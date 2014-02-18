(defn format-dt[dt-time]
  (let [ months ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"]
           day  (.substring dt-time 0 2)
           mon (+ (.indexOf months (.substring dt-time 3 6)) 1)
           yyyy (.substring dt-time 7 11)
           mon (if (< mon 10) (str "0" mon) mon)
           tt (.substring dt-time 12 20)]
    (clojure.instant/read-instant-timestamp  (str yyyy "-" mon "-" day "T" tt))))


(def sstr "92.168.34.11 - - [16/Feb/2014:06:25:19 +0000] \"GET /Pr\"")

(def dt (first (re-seq #"\d{1,2}/.../\d{4}:\d{2}:\d{2}:\d{2}" sstr)))
dt
(def months ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"])
(def day (.substring dt 0 2))
(def mon (str )(+ (.indexOf months (.substring dt 3 6)) 1))
(def yyyy (.substring dt 7 11) )

(String. "a" mon)
(.toString  mon)
(def mon (if (< mon 10) (str "0" mon) mon))

