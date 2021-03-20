package com.explorer.network;

import com.explorer.network.base.NetworkApi;
import com.explorer.network.beans.BaseResponse;
import com.explorer.network.errorhandler.ExceptionHandle;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;

/**
 * @author weixuefeng@diynova.com
 * @version $
 * @time: 3/20/21--12:03 PM
 * @description
 * @copyright (c) 2021 Newton Foundation. All rights reserved.
 */
public class NewtonNetworkApi extends NetworkApi {

	private NewtonNetworkApi() {
		super();
	}

	private static volatile NewtonNetworkApi mInstance;

	public static NewtonNetworkApi getInstance() {
		if(mInstance == null) {
			synchronized (NewtonNetworkApi.class) {
				mInstance = new NewtonNetworkApi();
			}
		}
		return mInstance;
	}

	public static <T> T getService(Class<T> service) {
		return getInstance().getRetrofit(service).create(service);
	}

	@Override
	protected <T> Function<T, T> getAppErrorHandler() {
		return response -> {
			//response中code码不会1 出现错误
			if (response instanceof BaseResponse && ((BaseResponse) response).errorCode != 1) {
				ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
				exception.code = ((BaseResponse) response).errorCode;
				exception.message = ((BaseResponse) response).errorMessage != null ? ((BaseResponse) response).errorMessage : "";
				throw exception;
			}
			return response;
		};
	}

	@Override
	protected Interceptor getInterceptor() {
		return null;
	}

	@Override
	public String getBaseUrl() {
		return "http://api.newtonproject.dev.diynova.com/";
	}
}
