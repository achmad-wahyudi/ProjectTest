package com.example.projecttest;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projecttest.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PostViewModel extends ViewModel {

    private final MutableLiveData<List<PostModel>> listMutableLiveData = new MutableLiveData<>();

    public LiveData<List<PostModel>> getDataPosts() {
        return listMutableLiveData;
    }

    public void setDataPosts() {
        List<PostModel> postModels = new ArrayList<>();
        ApiService.Api().getDataPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<PostModel>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // awal
                    }

                    @Override
                    public void onNext(@NonNull Response<List<PostModel>> listResponse) {
                        if (listResponse.isSuccessful()) {
                            if (listResponse.body() != null) {
                                postModels.addAll(listResponse.body());
                            }
                        }
                        listMutableLiveData.postValue(postModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        listMutableLiveData.postValue(postModels);
                    }

                    @Override
                    public void onComplete() {
                        // akhir
                    }
                });
    }
}
