package com.explorer.network_arch_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.explorer.network.NewtonNetworkApi;
import com.explorer.network.beans.BaseResponse;
import com.explorer.network.observer.BaseObserver;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Observable<BaseResponse> compose = NewtonNetworkApi.getService(NewtonApiInterface.class)
						.getCheckHealth()
						.compose(NewtonNetworkApi.getInstance().applySchedulers(new BaseObserver<BaseResponse>() {
							@Override
							public void onSuccess(BaseResponse baseResponse) {
								System.out.println(Thread.currentThread().getName());
								System.out.println(baseResponse);
							}

							@Override
							public void onFailure(Throwable e) {
								Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
							}
						}));
			}
		});
	}
}