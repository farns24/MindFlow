package com.farnsio.mindflow

import android.app.Application
import com.farnsio.mindflow.modules.DataModule
import com.farnsio.mindflow.ui.home.EditFragment
import com.farnsio.mindflow.ui.broken.HistoryFragment
import com.farnsio.mindflow.ui.graphs.GraphFragment
import dagger.BindsInstance
import dagger.Component


// Definition of the Application graph
@Component(modules = [DataModule::class])
interface ApplicationComponent {
    fun inject(graphFragment: GraphFragment)
    fun inject(editFragment: EditFragment)
    fun inject(historyFragment: HistoryFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?
        fun build(): ApplicationComponent?
    }

}

