package com.hao.lib.di.component;

import com.hao.lib.App;
import com.hao.lib.di.module.AppModule;
import com.hao.lib.rx.Api;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Yang Shihao.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(App app);

    App provideApplication();

    Api provideApi();
}
