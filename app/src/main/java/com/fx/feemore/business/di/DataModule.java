package com.fx.feemore.business.di;

import com.fx.feemore.business.http.ApiService;
import com.hengxian.baselib.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class DataModule
{

    @ApplicationScope
    @Provides
    public static ApiService provideApiService(Retrofit retrofit)
    {
        return retrofit.create(ApiService.class);
    }
}
