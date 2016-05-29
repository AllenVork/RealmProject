package zhangruofan.realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by zhangruofan on 16-5-24.
 */
class RealmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRealmConfig()
    }

    private fun initRealmConfig() {
        val config = RealmConfiguration.Builder(this)
                .name("MainRealm.realm")
                .schemaVersion(1)
//                .rxFactory()
                .build()
        Realm.setDefaultConfiguration(config)
    }
}