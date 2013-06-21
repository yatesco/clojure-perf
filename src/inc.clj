(ns inc
  (:gen-class))

(defn- inc-atom [n]
  (def x (atom 0))
  (dotimes [n n] (swap! x inc))
  @x)

(defn- array-let [n]
  (let [a (int-array n)]
    (dotimes [n n] (aset-int a n 1))
    (areduce a i ret 0
             (+ ret (aget a i)))))

(defn- array-def [n]
  (def a (int-array n))
  (dotimes [n n] (aset-int a n 1))
  (areduce a i ret 0
           (+ ret (aget a i))))

(defn- run-test [subject n]
  (time (do (def x (subject n)) (println x))))

(defn -main [& args]
  (let [n 1000000]
    (println "inc atom")
    (run-test inc-atom n)
    (println "array with let")
    (run-test array-let n)
    (println "array with def")
    (run-test array-def n))
)
