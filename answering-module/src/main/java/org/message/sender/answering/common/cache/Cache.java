package org.message.sender.answering.common.cache;

import java.util.Optional;

public interface Cache<K, T>
{
    Optional<T> getItem(K key);
}
