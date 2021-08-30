package edu.kpi.developmentmethods.storage;

import edu.kpi.developmentmethods.dto.Example;

import java.util.HashMap;

/**
 * Реализация хранилища в памяти.
 * Вам нужно реализовать персистентное хранилище, т.е. даные не должны
 * удаляться при перезапуске приложения.
 */
public class InMemoryStorageImpl implements Storage {
    private HashMap<String, Example> map = new HashMap<>();

    @Override
    public void del(String key) {
        this.map.remove(key);
    }

    @Override
    public void put(String key, Example value) {
        this.map.put(key, value);
    }

    @Override
    public Example get(String key) {
        return this.map.get(key);
    }
}
