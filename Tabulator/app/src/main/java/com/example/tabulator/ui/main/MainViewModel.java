package com.example.tabulator.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.tabulator.database.AppDatabase;
import com.example.tabulator.models.Tab;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {
    private AppDatabase database;
    private MutableLiveData<Boolean> saving = new MutableLiveData<>();
    private ObservableArrayList<Tab> entries = new ObservableArrayList<>();
    private MutableLiveData<Tab> currentTab = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        saving.setValue(false);
        database = Room.databaseBuilder(application, AppDatabase.class, "tabdb").build();

        new Thread(() -> {
            try{
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<Tab> tabs = (ArrayList<Tab>) database.getTabDao().getAll();
            entries.addAll(tabs);
        }).start();
    }

    public void deleteCurrentEntry(){
        new Thread(() -> {
            database.getTabDao().delete(currentTab.getValue());
            entries.remove(currentTab.getValue());
            currentTab.postValue(null);
        }).start();
    }

    public void createTab(String name, String notes){
        saving.setValue(true);
        new Thread(() -> {
            try{
                Thread.sleep(3000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            if (currentTab.getValue() != null) {
                Tab current = currentTab.getValue();
                current.name = name;
                current.notes = notes;
                database.getTabDao().update(current);
                currentTab.postValue(current);
                int index = entries.indexOf(current);
                entries.set(index, current);
            } else {
                Tab newEntry = new Tab();
                newEntry.name = name;
                newEntry.notes = notes;
                newEntry.id = database.getTabDao().insert(newEntry);
                entries.add(newEntry);
            }
            saving.postValue(false);

        }).start();
    }

    public MutableLiveData<Tab> getCurrentTab() {
        return currentTab;
    }

    public void setCurrentTab(Tab tab) {
        this.currentTab.setValue(tab);
    }

    public MutableLiveData<Boolean> getSaving() {
        return saving;
    }

    public ObservableArrayList<Tab> getEntries() {
        return entries;
    }
}