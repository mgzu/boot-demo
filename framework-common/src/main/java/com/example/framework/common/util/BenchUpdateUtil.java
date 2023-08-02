package com.example.framework.common.util;

import com.example.framework.common.options.BenchUpdateOptions;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
@UtilityClass
public class BenchUpdateUtil {

	public static <T> void benchUpdate(Collection<T> oldList, Collection<T> newList,
									   Function<T, String> uniqueKey, Consumer<T> insertAction,
									   Consumer<T> deleteAction, BiConsumer<T, T> updateAction) {
		benchUpdate(oldList, newList, uniqueKey, insertAction, deleteAction, updateAction, BenchUpdateOptions.defaults());
	}

	/**
	 * @param oldList      old data
	 * @param newList      new data
	 * @param uniqueKey    get unique key
	 * @param insertAction insert action
	 * @param deleteAction delete action
	 * @param updateAction update action
	 * @param <T>          data type
	 */
	public static <T> void benchUpdate(Collection<T> oldList, Collection<T> newList,
									   Function<T, String> uniqueKey, Consumer<T> insertAction,
									   Consumer<T> deleteAction, BiConsumer<T, T> updateAction,
									   BenchUpdateOptions options) {
		Map<String, T> oldMap = oldList.stream()
			.collect(Collectors.toMap(uniqueKey, Function.identity()));
		Map<String, T> newMap = newList.stream()
			.collect(Collectors.toMap(uniqueKey, Function.identity()));
		Set<String> oldKeySet = oldMap.keySet();
		Set<String> newKeySet = newMap.keySet();
		// If the new map contains the old key, it should be updated
		// otherwise need to delete
		for (String oldKey : oldKeySet) {
			if (newMap.containsKey(oldKey)) {
				T oldObj = oldMap.get(oldKey);
				T newObj = newMap.get(oldKey);
				if (!(options.isUpdateIgnoreEquals() && oldObj.equals(newObj))) {
					updateAction.accept(oldObj, newObj);
				}
			} else {
				deleteAction.accept(oldMap.get(oldKey));
			}
		}
		// Insert
		newKeySet.removeAll(oldKeySet);
		for (String newKey : newKeySet) {
			insertAction.accept(newMap.get(newKey));
		}
	}

}
