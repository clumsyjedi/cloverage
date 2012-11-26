(ns cloverage.debug
  (:use [clojure.java.io :only [writer]]))

(def ^:dynamic *debug*)
;; debug output
(defn tprn [& args]
  (when *debug*
    (do
      (apply prn args)
      (newline))))

(defn tprnl [& args]
  (when *debug*
    (apply println args)))

(defn tprf [& args]
  (when *debug*
    (apply printf args)))

(defn dump-instrumented [forms name]
  (when *debug*
    (with-open [ou (writer (str "debug-" name))]
      (binding [*out* ou
                *print-meta* true]
        (doall (map prn forms))))))
