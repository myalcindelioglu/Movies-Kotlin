# TMDB Movies By Kotlin

The Application is listing TMDB movies by infinite scrolling.
The Application also shows basic details of movie that is clicked by user. (Missing Now but I will add it)
In the application movies can be filtered by release date.
In the application movies are being cached by using room persistent database and refreshed with remote source data.

Flavors
------------------------------------
There are two flavors: **stub** and **prod**

**stub:** is using for testing and mocking the remote data sources.

**prod:** is using for production releases. 

Libraries used on this application
------------------------------------
* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Component](https://developer.android.com/topic/libraries/architecture/)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging/)
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
* [retrofit2](https://github.com/square/retrofit)
* [Dagger2](https://github.com/google/dagger)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [picasso](https://github.com/square/picasso)
* [junit, Mockito](http://site.mockito.org)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)

Patterns used on this application
------------------------------------
* [Model View Presenter - MVP](https://medium.com/@cervonefrancesco/model-view-presenter-android-guidelines-94970b430ddf)
* [Repo Pattern](https://medium.com/corebuild-software/android-repository-pattern-using-rx-room-bac6c65d7385)
