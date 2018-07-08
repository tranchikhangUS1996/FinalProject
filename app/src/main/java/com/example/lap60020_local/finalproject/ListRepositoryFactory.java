package com.example.lap60020_local.finalproject;

import com.example.lap60020_local.finalproject.ModelData.Repository.ListRepositorys.IListRepository;

import java.util.ArrayList;

public class ListRepositoryFactory {
    static final ArrayList<IListRepository> repositorys = new ArrayList<>();

    public static IListRepository get(String type) {
        for(IListRepository l : repositorys) {
            if(l.getName().equals(type)) return l;
        }
        return null;
    }

    public static void add(IListRepository iListRepository) {
        repositorys.add(iListRepository);
    }

}
