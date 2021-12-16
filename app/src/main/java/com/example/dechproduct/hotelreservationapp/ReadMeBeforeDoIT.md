1) Create Domain layer -> สร้าง UseCase class and Repository (สำหรับ pass เข้า constructor ของแต่ละ use case)
2) Create Data layer class  -> สร้าง sub package ชื่อ model 
    -> ไปเอามาจาก api ส่วนตัวอย่างนี้จะใช้ของ https://newsapi.org/ -> https://newsapi.org/v2/top-headlines?country=us&apiKey=0b09e45671b14657a5752f34383fdf82
    -> Copy all json and click at model package then Right click and select New ->  generate json to kotlin dataclass -> name APIResponse , Article , Source
    -> add gson at gradle ->   implementation 'com.google.code.gson:gson:2.8.9'

3) Modify repository interface  and UseCase class ที่ทำใน 1
    -> NewRepository inteface -> ใช้ coroutine ดังนั้นจึงเป็น suspending function 
        -> getNewsHeadLines มี return type เป็น api response 
            -> ทำ state ของ api response หน่อย -> loading ,success ,error -> create package name "util" -> and select sealed class -> name "Resource" 
                -> sealed class allow us to represent hierachies in the same file or as nested class
            -> เอา resource ที่พึ่งสร้างไปใช้กับ getNewsHeadlines function ซึ่ง return จาก ApiResponse เป็น Resource<ApiResponse>
            -> ทำตัวอื่นๆ save delete get แบบที่อธิบายไปใน class NewsRepo 
            -> ใน clean เราจะไม่ใช้ android framework ใน domain layer หมายความว่าใน repository กับ usecase เราสามาถใช้ได้แค่ kotlin language นะจ๊ะ
            
            -- จบ repository interface -> ทำ usecase class ต่อ
            
4) UseCase
    GetNewsHeadlines -> ทำ execute function ที return resource ไป แบเบดียวกับที่กำหนดใน repo interface
    GetSearchedNewsUseCase -> suspend fun execute(): Resource<APIResponse> {    return newsRepository.getNewsHeadlines()}
    SaveNewsUsecase ->   suspend fun execute(article: Article) = newsRepository.saveNews(article) // single line ได้เลย
    DeleteSavedNewUsecase ->     suspend fun execute(article: Article) = newRepository.deleteNews(article) // single line
    GetSavedNewsUseCase -> เราจะไม่ใข้ suspend เพราะ เรา return flow ->  fun  execute(): Flow<List<Article>> { return newsRepository.getSavedNews()  }

5) add API key กับ baseUrl 
    1. ใน gradle.properties -> MY_KEY = "0b09e45671b14657a5752f34383fdf82"
    2. build.gradle ->         buildConfigField("String", "API_KEY",MY_KEY)
    3. .gitignore -> gradle.properties
    4. gradle.properties  -> BASE_URL
    5. build.gradle -> buildConfigField("String", "BASE_URL",MY_URL)
    6.    buildTypes {
               release {
                   minifyEnabled true // here
                   shrinkResources true // here
                   proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
               }

6. Remote Data Source
    1. implement Usecase -> ex) getNewsHeadlinesUseCase and other usecase such as search get searched news and save news
        1.1 ) Create service interface with url and end points for retrofit -> Create api inside data package
        1.2) Inside api package create interface name NewsAPIService
            -> define function to get data from the new api with url end points and query parameters (อ่าน DOC : https://newsapi.org/docs/endpoints/top-headlines -> /v2/top-headlines) 
            ->  create function -> suspend fun, getTopHeadlines และ มี return type เป็น http response of type ก็คือ Response ของ retrofit ที่ครอบ <APIResponse> ที่เรา generate มานั้นเอง อย่าลืม import retrofit กับ gson converter ที่ gradle 
            -> ใส่ url end point กับ get http function ->  @GET("v2/top-headlines")
                                                              suspend fun getTopsHeadlines():Response<APIResponse>
                                                              
       1.3) Add query parameter -> ดูใน doc ว่าเอาไรมั้ง ในนี้เอา country page apikey   
       interface NewsAPIService {
           @GET("v2/top-headlines")
           suspend fun getTopsHeadlines(
               @Query("country")        <--ใส่เครื่องหมาย @Query เวลาจะเอา api และตัวข้างใน เช่น "country" ให้ดูจาก doc ใส่ให้ตรงกัน
               country: String,
            
               @Query("page")
               page: Int,
             
               @Query("apiKey")
               apiKey:String = BuildConfig.API_KEY <-- ทำไว้แล้วใน 5) คือสร้าง API_KEY ใน build config 
           ):Response<APIResponse>
       
       Get value จาก build config ได้แล้ววว
       ไปทำ component อื่นๆที่ require การ get Data จาก API ได้เลยย
       }                                                    
    
7) สร้าง repository package ใน data package ที่มี 2 sub package datasource and datasourceImpl
    1. datasource -> interface NewRemoteDataSource -> ในนี้ใช้กำหนด abstract function เพิ่อ ติดต่อกับ api
            ให้กำหนด 
            interface NewRemoteDataSource {
                suspend fun getTopHeadkines():Response<APIResponse>    //return type เป็น response ของ type APIResponse
            }
    2.datasourceImpl -> สร้าง new class ที่ implement ตัว interface ที่เราพึ่งสร้างไปใน NewRemoteDataSource   
        class NewsRemoteDataSourceImpl(): NewsRemoteDataSource{
            override suspend fun getTopHeadLines(): Response<APIResponse> { //override มา
                TODO("Not yet implemented")
            }   
        } 
        ใน service interface (์์NewsAPIService) เรามี 3 parameter คืิอ country , page , apiKey  
        เรา provide apikey  เสดละ เหลือ String contry กับ Int page number 
        
    3.    ใน NewsRemoteDataSourceImpl
            ให้ add 3ตัวข้างบนนั้นรับเข้ามาเป็น constructor parameter   
            แบบนี้ 
                class NewsRemoteDataSourceImpl(
                    private val country: String, <-- here
                    private val page:Int,        <-- here
                    private val newsAPIService: NewsAPIService  <-- apikey อันนี้ใช้ของ service ที่เราทำไป
                ): NewsRemoteDataSource{
                    override suspend fun getTopHeadLines(): Response<APIResponse> {
                               return newsAPIService.getTopsHeadlines(country,page)
                                            <-- เท่านี้เราก็จะสามารถ geTopHeadlines function ของ NewsAPIService โดยการ pass ค่า country กับ page id เป็น argument สำเร็จ

                    }
                }
    4. สร้าง NewsRepositoryImpl ไว้ใน repository class           
    
        class NewsRepositoryImpl(
           private val newsRemoteDataSource: NewsRemoteDataSource                             // add instance of NewsRemoteDataSource as a Constructor parameter
        ): NewsRepository {
            override suspend fun getNewsHeadlines(): Resource<APIResponse> {
                TODO("Not yet implemented")
            }
        
            override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
                TODO("Not yet implemented")
            }
        
            override suspend fun saveNews(article: Article) {
                TODO("Not yet implemented")
            }
        
            override suspend fun deleteNews(article: Article) {
                TODO("Not yet implemented")
            }
        
            override fun getSavedNews(): Flow<List<Article>> {
                TODO("Not yet implemented")
            }
        }
        
        4.1) สร้าง function เพื่อ input the response instance of type APIResponse ที่ return จาก api และ output resource instance of type APIResponse
            
            class NewsRepositoryImpl(
                private val newsRemoteDataSource: NewsRemoteDataSource
            ) : NewsRepository {
            
                private fun responseResource(response: Response<APIResponse>): Resource<APIResponse> {
                    if (response.isSuccessful) {
                        response.body()?.let { result ->  // ต้องการเก็บ body ของ response และ return มันว่า success or fail
                                return Resource.Success(result)
                            }
                    }
                    return Resource.Error(response.message())
                }
        4.2  ทำ getNewsHeadline() function     
                override suspend fun getNewsHeadlines(): Resource<APIResponse> {
                      return responseResource(newsRemoteDataSource.getTopHeadLines()) 
                                                                     // (newsRemoteDataSource.getTopHeadLines() มันจะ return response object เรา convert ให้เป็น resource object
                  }

        เราทำ datasource กับ repository function ที่เชื่อมกับ get news headlines usecase เสดแล้ว
        จบ network relation related component
            
8) Create view model 
  1)  ต้องการ pass country กับ page id จาก view model เพื่อ facilitate the process  -> define เป็น constructor parameter ที่getTopHeadLines ใน NewsRemoteDataSource interface
    เดืม
        interface NewsRemoteDataSource {
            suspend fun getTopHeadLines():Response<APIResponse>//return type เป็น response ของ type APIResponse
        }
    ใหม่ 
        interface NewsRemoteDataSource {
            suspend fun getTopHeadLines(country: String, page: Int ):Response<APIResponse>//return type เป็น response ของ type APIResponse
        }    
        
     เสร็จแล้วเปลี่ยน NewRemoteDataSourceImpl
     เดิม
        class NewsRemoteDataSourceImpl(
            private val country: String,
            private val page:Int,
            private val newsAPIService: NewsAPIService
        ): NewsRemoteDataSource{
            override suspend fun getTopHeadLines(): Response<APIResponse> {
                return newsAPIService.getTopsHeadlines(country,page)
            }
        }
     ใหม่ ลบพวก viriable ออก   
     class NewsRemoteDataSourceImpl(
     
         private val newsAPIService: NewsAPIService
     ): NewsRemoteDataSource{
         override suspend fun getTopHeadLines(): Response<APIResponse> {
             return newsAPIService.getTopsHeadlines(country,page)
         }
     }
     
     เปลี่ยน newsRepository interface และ newsRepositoryImpl สำหรับ new parameter รวมถึง execute ของ GetXXUseCase ด้วย ให้รับเป็น execute(x,y)
     
     2) เสร็จแล้ว สร้าง viewModel class name SearchReservationViewModel
        เราต้องการที่จะเขียน function เพื่อ get the list of news head lines ในรูปแบบ mutable live data ของ type Resource 
        (resource class ที่เราสร้างมาเก็บ track ของ state ของ ApiResponse)
        -> อาจจะ success / still loading or get some error -> ถ้าโหลดอยุ่ ให้แสดง progress bar ถ้า error แสดง toast เป็น message ที่ทำให้ error
        
        2.1 Create ViewModel 
            -> extend ViewModel
             class xx:ViewModel() {
            }
        2.2    define mutable live data สำหรับ news head line results ที่ได้จาก api -> resource ของ ApiResponse = MutableLiveData()
            val newsHeadlines : MutableLiveData<Resource<APIResponse>> = MutableLiveData()
        
        2.3) สร้าง get Breaking news function ซึ่งควรจะมี string country name และ page number
        ข้างใน function เราจะใช้สำหรับทำเรื่อง networking task -> เราต้องการที่จะใข้ seperate thread -> coroutine -> viewmodelscope.launch (อย่าลืม import viewmodel กับ livedata ก่อนใช้งานนะ)
        
              fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch (Dispatchers.IO) {   <-- here จะทำให้ function เราทำงานแบบ background thread
               newsHeadlines.postValue(Resource.Loading())                                                  1) set status เป็น loading
               val api  = ?                                                                                  2) get successful response
                                                                                                           -> ซึ่งต้องใข้ instance ของ getNewsHeadlinesUseCase  ดังนั้นก็เลย
               3)                                                                                                 |
                class SearchReservationViewModel(                                                                 |
                    val getNewHeadlinesUseCase: GetNewHeadlinesUseCase                                          -> add เป็น constructor parameter บน class viewmodel เลย
                ) : ViewModel() {                                                         
      
                val newsHeadlines : MutableLiveData<Resource<APIResponse>> = MutableLiveData()

                fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch (Dispatchers.IO) {
                    newsHeadlines.postValue(Resource.Loading())
                      val apiResult = getNewHeadlinesUseCase.execute(country, page)                             <- นี้ หลังจากนั้นก็ pass value country, page เข้าไป
                       newsHeadlines.postValue(apiResult)                                                       หลังจากนั้นก็ post ไป
                }
                        }
                        
                จบ normal flow แล้ววว แค่อย่าลืมดัก case สำหรับ unexpect situation   -> net ไม่มี IO exception
                เลยสร้าง function สำหรับ checkInternet -> internet connect => return true 
                อย่าลืม pass context เป็น parameter ส่วนวิธีเช็คก็ ดูเนตเอา (ตอน check internet)
              
       2.4) สร้าง application context -> val app: Application,
                -> เปยี่ยน :ViewModel เป็น AndroidViewModel เพื่อเรียกใช้ context ใน  isInternetAvailable(context: Context)
                หลังจากนั้นก็ pass ไป ในที่มัน require
                ex)
                ) : AndroidViewModel(app) {
                
                fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch (Dispatchers.IO) {
                       if(isInternetAvailable(app)){ <--
               
               หลังจากนั้นก็ handle logic try catch ไร ว่าไป -> จบ viewModel class 
               -> เราจะเพิ่ม function related  to other use case later
               
  9) Create viewmodel factory สำหรับ viewmodel ตัวนี้
  
  class SearchReservationViewModelFactory(
 
 10) ใข้ dependency injection -> Hilt (https://developer.android.com/training/dependency-injection/hilt-android)
 1. import hilt -> root of gradle ->         classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
             -> build.gradle
 
 2. create hilt -> All app must contain Application Class that annotated with @HiltAndroidApp  that trigger hilt code generator           
            -> Create Class NewsApp that extends Application แล้ว annotate ด้วย ว่า
            
        @HiltAndroidApp
        class NewsApp: Application() 
3.  manifest -> android:name=".presentation.hotel.reservation.reservation_search.NewsApp"

4. สร้าง package call di ไปดูส่วน 18: 140 dependency+Hilt
        
11. Create Fragment Part display (Dech)
    using viewBinding -> SearchReservationActivity เป็น base activity ที่ใช้กับหลายๆ fragment
12. Create Nav Graph  NewsFragment InfoFragment SavedFragment
13. Create Recycler View ในหน้าที่อยากให้แสดง -> 
    13.1 สร้าง List Item layout ก่อน คือหน้า news_list_item
    13.2 สร้าง recycler view + progress bar ในหน้าที่อยากให้แสดง คือหน้า fragment_news
    13.3 สร้าง adapter class กับ recycler view ใข้ dift util for handle the performance ไม่ให้มันโหลดซ้ำ คือหน้า NewsAdapter
    13.4 กลับไปหน้าที่อยากแสดง list คือหน้า NewsFragment แล้วเขียนโค้ดเพื่อแสดง data ที่สร้างที่ adapter
    
14.  Relation 1 Activity and a lot of fragment -> Construct an instance of viewModel inside the activity and share it among fragments
    -> Define ViewModel at SearchReservationActivity   
        -> สร้าง viewmodel ต้องใข้ viewmodel factory ด้วย -> ใช้ dependency injection
            -> สร้าง dagger hilt เพื่อ provide the viewmodel factory -> annotate class ที่จะใช้ด้วย  @AndroidEntryPoint
                -> กำหนด SearchReservationViewModel Factory instance -> annotate with @Inject annotation
                    -> เขียน code เพื่อ get viewmodel โดยใช้ viewmodel provider -> 
                    -> กลับไปที่  newsfragment และเขียน code เพื่อ get viewmodel instance  ที่เราสร้างจาก activity นี้เอาไปใข้ต่อ
                        -> Generate onViewCreated สำหรับ fragment ที่จะเอาไปใช้ (ใข้ onViewCreated เพร่าะว่ามันจะถูก call ทันทีหลังจากเมื่อทุก view ถูกสร้างแล้ว ซึ่งจะปลอดภัยเพราะช่วยเลี่ยงพวก error ได้)
    -> NewsFragment
        -> หลังจาก generate onViewCreated แล้ว หลังจากนั้นเราจะกำหนด viewModel ที่เอามาจาก activity ที่เราสร้างไปมาใข้ใน fragment ที้ต้องการ
              -> viewModel = (activity ad MainActivity).viewModel    
        -> เขียน code สำหรับ get binding object สำหรับ viewbinding    
          ex)     private lateinit var fragmentNewsBinding: FragmentNewsBinding 
          
                   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                          super.onViewCreated(view, savedInstanceState)
                          fragmentNewsBinding = FragmentNewsBinding.bind(view) <-- here
                          viewModel = (activity as SearchReservationActivity).viewModel
                      }
        -> สร้าง function สำหรับ initialize recycler view ใน fragment ที่เราต้องการแสดง
            -> สร้าง adapter instance object (ที่ทำมาแล้ว) เพื่อ assign ไปที่ recyclerview adapter แล้วก็ set recyclerview layout เป็น linear layout manager -> ใช้ apply function kotlin
            -> สร้าง 2 ฟังก์ชันสำหรับ view / hide progress bar   
            -> สร้าง function สำหรับแสดงผล newslist  -> fun viewNewsList()  
                    -> เริ่มเขียนโดยการเรียก getNewsHeadLines function ที่ทำไว้ใน viewModel มาใช้เลย -> ต้อง pass country name กับ page number เป็น argument
                            class SearchReservationViewModel()
                                ...
                            fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch (Dispatchers.IO) { <-- here
                                    newsHeadlines.postValue(Resource.Loading())
                                    try{
                                        if(isInternetAvailable(app)){
                                            val apiResult = getNewHeadlinesUseCase.execute(country, page)
                                            newsHeadlines.postValue(apiResult)
                                        }else{
                                            newsHeadlines.postValue(Resource.Error("Internet is not available"))
                                        }
                                        }catch (e:Exception){
                                            newsHeadlines.postValue(Resource.Error(e.message.toString()))
                                    }
                    -> สร้างฟังก์ชัน get list จาก viewmodel
                            private val country = "us"
                            private val page = 1
                            .
                            private fun viewNewsList() { <--here
                                    viewModel.getNewsHeadLines(country,page)
                                }
                
           ->  observe newsHeadLines Mutable LiveData ที่มาจาก viewModel
                
                class SearchReservationViewModel(
                
                    private val app: Application,
                    private val getNewHeadlinesUseCase: GetNewHeadlinesUseCase
                ) : AndroidViewModel(app) {
                
                    val newsHeadlines : MutableLiveData<Resource<APIResponse>> = MutableLiveData() <--  นี้ here

                class SearchReserActivity
                    .
                    .
                   private fun viewNewsList() {                                             <--here
                        viewModel.getNewsHeadLines(country,page)
                        viewModel.newsHeadLines.observe(viewLifecycleOwner, {response ->    // <-- มานี้ here
                        when (response){                                                     <--สำหรับเขียน status success error loading จาก Resource class
                            is Resource.Success -> {                                        // success ให้ hide progressbar และ ส่ง received data ไปที่ adapter
                                 hideProgressBar()
                                 response.data?.let{
                                        newsAdapter.differ.submitList(it.articles.toList())
                                 }
                            }
                             is Resource.Error -> {)                                         // Error ให้ hide progressbar และ Show toast message ที่แสดง error
                             is Resource.Loading -> {)                                      // loading ให้ show progressbar 
                        )
                    }
                    
                    // เสร็จหน้า load data มา show
         


            
            
    