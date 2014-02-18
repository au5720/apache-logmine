 (ns apache-logmine.utils)


 (defn format-dt[dt-time]
  (let [ months ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"]
           day  (.substring dt-time 0 2)
           mon (.indexOf months (.substring dt-time 3 6))
           yyyy (.substring dt-time 7 11)
           mon (if (< mon 10) (str "0" mon) mon)
           tt (.substring "17/Feb/2014:00:31:50 +0000" 12 20)]
    (clojure.instant/read-instant-timestamp  (str yyyy "-" mon "-" day "T" tt))))

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
