package com.farnsio.mindflow

import android.app.Application
import com.farnsio.mindflow.modules.DataModule
import com.farnsio.mindflow.ui.home.EditFragment
import com.farnsio.mindflow.ui.broken.Broken
import dagger.BindsInstance
import dagger.Component


// Definition of the Application graph
@Component(modules = [DataModule::class])
interface ApplicationComponent {
    fun inject(broken: Broken)
    fun inject(editFragment: EditFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?
        fun build(): ApplicationComponent?
    }

}

