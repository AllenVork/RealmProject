package zhangruofan.realm.view.activities

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import kotlinx.android.synthetic.main.activity_rx.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import zhangruofan.realm.R
import zhangruofan.realm.model.Cat
import zhangruofan.realm.util.CatUtil

/**
 * Created by zhangruofan on 16-5-24.
 */
class RxjavaActivity : BaseActivity() {

    private val TAG = RxjavaActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        layout = R.layout.activity_rx
        super.onCreate(savedInstanceState)
        CatUtil.initCatsAsync(realm)
        getFirstCat(Action1 {
            catsTv.text = "id = ${it.id} name = ${it.name}"
        })
        initView()
    }

    private fun initView() {
        catsTv.movementMethod = ScrollingMovementMethod.getInstance()

        descriptionTv.text = resources.getString(R.string.rx_description)
    }

    /**
     * get all the cats asynchronous
     */
    private fun getFirstCat(action: Action1<Cat>) {
        realm.where(Cat::class.java)
                .findAllAsync()
                .asObservable()
                .filter {
                    Log.e(TAG, "start filtering")
                    it.isLoaded
                }
                .flatMap {
                    Observable.just(it.first())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (action, Action1 { Log.e(TAG, "error when get cats:$it") })
    }
}