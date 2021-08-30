package edu.kpi.developmentmethods.storage;

import edu.kpi.developmentmethods.dto.Example;

/**
 * Пример интерфейса хранилища.
 *
 * Вы можете изменить этот интерфейс,
 * чтоб он лучше подходил под ваши задачи.
 */
public interface Storage {
    /**
     * Удаляет элемент из хранилища по ключу
     *
     * @param key ключ
     */
    public void del(String key);

    /**
     * Сохраняет элемент в хранилище по ключу
     *
     * @param key ключ
     * @param value значение
     */
    public void put(String key, Example value);

    /**
     * Возвращает элемент из хранилища по ключу
     *
     * @param key ключ
     * @return значение из хранилища
     */
    public Example get(String key);
}
