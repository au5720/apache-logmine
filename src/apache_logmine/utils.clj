 (ns apache-logmine.utils)


 (defn make-number[n]
  (try (Integer. n)
    (catch Exception e 0)))

(defn get-bytes[ip-data]
  (let [bytes-total (->> ip-data
                   (map #(make-number (% :bytes)))
                   (reduce +))
        ip ((first ip-data) :ip)]
    {bytes-total ip}))

(defn normalize-num[n]
  (int (/ n (* 1024 1024))))

(defn top-ten[data]
  (->> data
       keys
       vec
       distinct
       (map #(get-bytes (data %)))
       (apply conj)
       sort
       reverse
       (take 10)
       (map reverse)))

(defn summer[d]
  (->> (val d)
                 (map #(make-number (:bytes %1)))
                 (apply +)))

(defn total-bytes[data]
  (apply + (map summer   data)))
