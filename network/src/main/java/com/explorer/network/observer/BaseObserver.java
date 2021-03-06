package com.explorer.network.observer;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author weixuefeng@diynova.com
 * @version $
 * @time: 3/20/21--2:32 PM
 * @description
 * @copyright (c) 2021 Newton Foundation. All rights reserved.
 */
public abstract class BaseObserver<T> implements Observer<T> {

	@Override
	public void onSubscribe(@NonNull Disposable d) {

	}

	@Override
	public void onNext(@NonNull T o) {
		onSuccess(o);
	}

	@Override
	public void onError(@NonNull Throwable e) {
		onFailure(e);
	}

	@Override
	public void onComplete() {

	}

	public abstract void onSuccess(T t);

	public abstract void onFailure(Throwable e);
}
