(ns clojure.lang.ratio
  (:refer-clojure :only [defn deftype])
  (:require [clojure.lang.protocols :refer [-denominator -numerator]]))

(defn numerator [ratio]
  (-numerator ratio))

(defn denominator [ratio]
  (-denominator ratio))