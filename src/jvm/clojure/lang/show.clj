(ns clojure.lang.show
  (:refer-clojure :only [defmacro extend-protocol fn defn list loop update-in cons])
  (:require [clojure.lang.protocols :refer [IShow -show]]
            [clojure.next           :refer :all :exclude [list cons]])
  (:import [java.lang StringBuilder]))

(defn platform-show-method [methods init-macro]
  (update-in methods
             ['Object]
             (fn [old]
               (cons
                 (list 'toString ['this]
                       (list init-macro 'this))
                 old))))

(extend-protocol IShow
  Object
  (-show [this]
    (.toString this)))

(defmacro build-string [strs]
  `(loop [sb# (StringBuilder. "") strs# ~strs]
     (if (empty? strs#)
       (.toString sb#)
       (recur (.append sb# (str (first strs#))) (rest strs#)))))

(defmacro show-method [bindings & body]
  `(toString ~bindings ~@body))

