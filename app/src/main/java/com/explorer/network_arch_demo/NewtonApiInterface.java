package com.explorer.network_arch_demo;
import com.explorer.network.beans.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
/**
 * @author weixuefeng@diynova.com
 * @version $
 * @time: 3/20/21--5:58 PM
 * @description
 * @copyright (c) 2021 Newton Foundation. All rights reserved.
 */
public interface NewtonApiInterface {
	@GET("api/v1/health/")
	Observable<BaseResponse> getCheckHealth();

}
