(ns clojure.lang.hash
  (:refer-clojure :only [defmacro extend-protocol extend-type fn defn list update-in cons])
  (:require [clojure.lang.protocols :refer [IHash]])
  (:import [clojure.lang Util]))

(defn hash-combine [hash1 hash2]
  (Util/hashCombine hash1 hash2))

(defn hash-code [this]
  (.hashCode this))

(extend-protocol IHash
  Object
  (-hash [this]
    (.hashCode this)))

(extend-type nil
  IHash
  (-hash [this] 0))

(defmacro hash-method [bindings & body]
  `(hashCode ~bindings ~@body))

