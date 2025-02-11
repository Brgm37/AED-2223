package serie2.mapCollections

operator fun <K, V> AEDMutableMap<K, V>.set(key: K, value: V){
   put(key, value)
}

operator fun <K,V> AEDMutableMap<K, V>.contains(k:K):Boolean{
    return containsKey(k)
}

