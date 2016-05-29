package zhangruofan.realm.util

import android.util.Log
import android.util.TimingLogger
import io.realm.Realm
import zhangruofan.realm.model.Cat
import java.util.*

/**
 * Created by zhangruofan on 16-5-25.
 */
object CatUtil {

    val TAG = CatUtil::class.java.simpleName

    init {
        Log.d(TAG, "Is Loggable? " + Log.isLoggable(TAG, Log.VERBOSE));
    }

    /**
     * save 1000 cats to realm **asynchronously**
     * this method won't block the main thread but it takes more time to save data
     */
    fun initCatsAsync(realm: Realm) {
        val timeLogger = TimingLogger(TAG, "initCats")

        Log.d(TAG, "Is Loggable? " + Log.isLoggable(TAG, Log.VERBOSE));

        var cats = LinkedList<Cat>()

        timeLogger.addSplit("initCats")

        for (i in 0..1000) {
            val cat = Cat(i, "cat$i")
            cats.add(cat)
        }

        timeLogger.addSplit("create and save 1000 cats to LinkdedList")

        //save the cats to realm asynchronously
        //realm can only be accessed on the thread where it's created
        //so you can't use the original realm object to save POJO on the other thread
        realm.executeTransactionAsync(Realm.Transaction { realm ->
            //the realm object in this block is not the original one
            //this methods almost takes 1000ms which is 2x slower than the synchronous method
            realm.copyToRealmOrUpdate(cats)
        }, Realm.Transaction.OnSuccess {
            timeLogger.addSplit("save the list to Realm successfully")
            timeLogger.dumpToLog()
        })

        timeLogger.addSplit("start save cats to realm")
    }


    /**
     * save 1000 cats to realm on the main thread
     */
    fun initCatsSync(realm: Realm) {
        val timeLogger = TimingLogger(TAG, "initCatsAsync")

        var cats = LinkedList<Cat>()

        timeLogger.addSplit("init cats list")

        //this method almost takes about 10ms
        for (i in 0..1000) {
            val cat = Cat(i, "cat$i")
            cats.add(cat)
        }

        timeLogger.addSplit("create and save 1000 cats to LinkdedList")

        realm.executeTransaction {
            //this method almost takes about 500ms
            realm.copyToRealmOrUpdate(cats)
        }
        timeLogger.addSplit("save cats to realm successfully")
        timeLogger.dumpToLog()
    }
}