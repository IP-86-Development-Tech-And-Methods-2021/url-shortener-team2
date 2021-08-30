package edu.kpi.developmentmethods.logic;

import edu.kpi.developmentmethods.dto.Example;
import edu.kpi.developmentmethods.storage.Storage;


/**
 * Класс, который инкапсулирует в себя бизнес-логику приложения
 */
public class Logic {
    private final Storage storage;

    public Logic(Storage storage) {
        // Обратите внимание, мы не создаём хранилище внутри класса, а получаем его извне.
        // Такой подход используется для построения более гибкой архитектуры приложения,
        // и называется внедрением зависимостей (dependency injection)
        this.storage = storage;
    }

    /**
     * Получаем элемент из хранилища по ключу
     *
     * @param key ключ
     * @return значение, которое храилось по указаному ключу
     */
    public Example getExample(String key) {
        return storage.get(key);
    }


    /**
     * Сохраняет значение по ключу, если нет других значений, сохранённых с этим ключом
     *
     * @param key ключ по которому значение должно быть сохранено
     * @param value значение
     * @return true если значение было сохранено, в противном случае - false
     */
    public synchronized boolean putExampleIfNotExists(String key, Example value) {
        var example = storage.get(key);
        if (example == null) {
            storage.put(key, value);
            return true;
        }
        return false;
    }


    /**
     * Удаляет элемент по ключу. В случае, если по даному ключу нет сохранённого значения,
     * этот метод ничего не делает
     *
     * @param key ключ
     */
    public synchronized void deleteExample(String key) {
        storage.del(key);
    }
}
