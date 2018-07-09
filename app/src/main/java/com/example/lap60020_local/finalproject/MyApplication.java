package com.example.lap60020_local.finalproject;

import android.app.Application;

import com.example.lap60020_local.finalproject.ModelData.Repository.AccountRepository.AccountRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.DetailRepository.DetailRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.GenreRepository.GenreRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.FavouriteRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.GenreMovieRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.PopularRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.RatedRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.RecommendRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.SearchRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.SimilarRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.TopratedRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.WatchlistMovieRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.LoginRepository.LoginRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository.AddToWatchListRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository.MaskAsFovoriteRepository;
import com.example.lap60020_local.finalproject.ModelData.Repository.UserInteractRepository.RateRepository;
import com.example.lap60020_local.finalproject.ModelData.retrofit.AuthenticationAPI;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MovieAPI;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MyApiClient;
import com.example.lap60020_local.finalproject.ViewModel.AccountViewModel;
import com.example.lap60020_local.finalproject.ViewModel.AddToWatchListViewModel;
import com.example.lap60020_local.finalproject.ViewModel.DetailViewModel;
import com.example.lap60020_local.finalproject.ViewModel.GenreViewModel;
import com.example.lap60020_local.finalproject.ViewModel.ListViewModel;
import com.example.lap60020_local.finalproject.ViewModel.LoginViewModel;
import com.example.lap60020_local.finalproject.ViewModel.MaskAsFavoriteViewModel;
import com.example.lap60020_local.finalproject.ViewModel.MoviesViewModel;
import com.example.lap60020_local.finalproject.ViewModel.RateViewModel;
import com.example.lap60020_local.finalproject.ViewModel.SearchViewModel;

public class MyApplication extends Application {

    private String sessionId = "";
    private String accountId = "";
    private MovieAPI movieAPI;
    private AuthenticationAPI authenticationAPI;
    private String Apikey = MyApiClient.MY_KEY;
    private GenreViewModel genreViewModel;
    private MoviesViewModel popularViewModel;
    private MoviesViewModel topratedViewModel;
    private MoviesViewModel searchViewModel;
    private LoginViewModel loginViewModel;
    private DetailViewModel detailViewModel;
    private MoviesViewModel similarViewModel;
    private MoviesViewModel recommendedViewModel;
    private AccountViewModel accountViewModel;
    private RateViewModel rateViewModel;
    private MaskAsFavoriteViewModel maskAsFavoriteViewModel;
    private AddToWatchListViewModel addToWatchListViewModel;

    private GenreRepository genreRepository;
    private IListRepository popularRepository;
    private IListRepository topratedRepository;
    private IListRepository searchRepository;
    private LoginRepository loginRepository;
    private DetailRepository detailRepository;
    private IListRepository similarRepository;
    private IListRepository recommendRepository;
    private AccountRepository accountRepository;
    private RateRepository rateRepository;
    private MaskAsFovoriteRepository maskAsFovoriteRepository;
    private AddToWatchListRepository addToWatchListRepository;
    private FavouriteRepository favouriteRepository;
    private WatchlistMovieRepository watchlistMovieRepository;
    private RatedRepository ratedRepository;


    void initFactory() {
        ListRepositoryFactory.add(new GenreMovieRepository(movieAPI,Apikey));
        ListRepositoryFactory.add(similarRepository);
        ListRepositoryFactory.add(recommendRepository);
        ListRepositoryFactory.add(searchRepository);
        ListRepositoryFactory.add(popularRepository);
        ListRepositoryFactory.add(topratedRepository);
        ListRepositoryFactory.add(ratedRepository);
        ListRepositoryFactory.add(watchlistMovieRepository);
        ListRepositoryFactory.add(favouriteRepository);

    }


    @Override
    public void onCreate() {
        super.onCreate();
        movieAPI = MyApiClient.getInstance().create(MovieAPI.class);
        authenticationAPI = MyApiClient.getInstance().create(AuthenticationAPI.class);

        genreRepository = new GenreRepository(movieAPI, Apikey);
        popularRepository = new PopularRepository(movieAPI, Apikey);
        topratedRepository = new TopratedRepository(movieAPI, Apikey);
        searchRepository = new SearchRepository(movieAPI, Apikey);
        loginRepository = new LoginRepository(authenticationAPI, Apikey);
        detailRepository = new DetailRepository(movieAPI, Apikey);
        similarRepository = new SimilarRepository(movieAPI, Apikey);
        recommendRepository = new RecommendRepository(movieAPI, Apikey);
        accountRepository = new AccountRepository(authenticationAPI, Apikey);
        rateRepository = new RateRepository(movieAPI,Apikey);
        maskAsFovoriteRepository = new MaskAsFovoriteRepository(movieAPI,Apikey);
        addToWatchListRepository = new AddToWatchListRepository(movieAPI, Apikey);
        favouriteRepository = new FavouriteRepository(movieAPI, Apikey);
        watchlistMovieRepository = new WatchlistMovieRepository(movieAPI, Apikey);
        ratedRepository = new RatedRepository(movieAPI,Apikey);

        rateViewModel = new RateViewModel(rateRepository);
        addToWatchListViewModel = new AddToWatchListViewModel(addToWatchListRepository);
        maskAsFavoriteViewModel = new MaskAsFavoriteViewModel(maskAsFovoriteRepository);
        genreViewModel = new GenreViewModel(genreRepository);
        popularViewModel = new ListViewModel(popularRepository);
        topratedViewModel = new ListViewModel(topratedRepository);
        searchViewModel = new SearchViewModel(searchRepository);
        loginViewModel = new LoginViewModel(loginRepository);
        detailViewModel = new DetailViewModel(detailRepository);
        similarViewModel = new ListViewModel(similarRepository);
        recommendedViewModel = new ListViewModel(recommendRepository);
        accountViewModel = new AccountViewModel(accountRepository);

        initFactory();
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public GenreViewModel getGenreViewModel() {
        return genreViewModel;
    }

    public MoviesViewModel getPopularViewModel() {
        return popularViewModel;
    }

    public MoviesViewModel getTopratedViewModel() {
        return topratedViewModel;
    }

    public MoviesViewModel getSearchViewModel() {
        return searchViewModel;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public DetailViewModel getDetailViewModel() {
        return detailViewModel;
    }

    public MoviesViewModel getSimilarViewModel() {
        return similarViewModel;
    }

    public MoviesViewModel getRecommendedViewModel() {
        return recommendedViewModel;
    }

    public String getSessionID() {
        return sessionId;
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public RateViewModel getRateViewModel() {
        return rateViewModel;
    }

    public MaskAsFavoriteViewModel getMaskAsFavoriteViewModel() {
        return maskAsFavoriteViewModel;
    }

    public AddToWatchListViewModel getAddToWatchListViewModel() {
        return addToWatchListViewModel;
    }
}
