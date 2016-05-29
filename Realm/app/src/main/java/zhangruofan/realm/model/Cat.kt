package zhangruofan.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by zhangruofan on 16-5-25.
 */
open class Cat(
        @PrimaryKey
        open var id: Int = 0,
        open var name: String = ""
) : RealmObject() {
    //The kotlin compiler generates standard getters and setters
    //Realm will overload them and code inside them is ignored
    //So if you prefer you can just have empty abstract methods
}