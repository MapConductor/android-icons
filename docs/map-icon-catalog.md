# MapConductor Map Icon Catalog (Research Draft)

調査日: 2026-08-14
対象: `android-icons` / `ios-icons` / `react-icons` の共通アイコン候補
候補数: **300 glyphs**

## 目的

地図SDK自体が描画する道路・地形・行政界ではなく、MapConductorのマーカー内に表示する小さなPOI glyphの候補を整理する。たとえば病院なら、既存のデフォルトピン内に `hospital` glyphを置く。写真の場合は、角丸長方形へ画像をcropしてアンカー用のtailを付ける。

この一覧は実装名を固定する最終仕様ではなく、既存の地図アイコンセットとPOI分類を統合した調査ドラフトである。既存セットの図形やpathは転用せず、採用するglyphはすべてMapConductor用に新規制作する。パッケージ境界、地域別コレクション、命名規則は[Icon Collection Architecture](icon-architecture.md)を正とする。

## 調査した主要資料

- [Mapbox Maki](https://github.com/mapbox/maki): 地図製作者向けの15px POIアイコン。一般的なPOIを優先し、ファイル名をOpenStreetMap tagに合わせる設計。CC0-1.0。
- [Rapid/Temaki](https://github.com/rapideditor/temaki): Makiで不足する用途を補う15px拡張セット。小さなmap pinで判別できる単純な形を推奨。CC0。
- [Google Places API Place Types](https://developers.google.com/maps/documentation/places/web-service/place-types): Automotive、Culture、Food and Drink、Health、Shopping、Transportationなど、実サービスで使われるPOI分類。
- [Google Places API Place Icons](https://developers.google.com/maps/documentation/places/web-service/icons): glyphとカテゴリ背景色を分離する実例。配信URLは変更され得るため、同梱素材ではなく表示モデルの参考とする。
- [OpenStreetMap Map Features](https://wiki.openstreetmap.org/wiki/Map_Features): `amenity=*`、`shop=*`、`tourism=*`、`healthcare=*`などの実地物分類。
- [OpenStreetMap Carto Symbols](https://wiki.openstreetmap.org/wiki/SymbolsTab): 標準地図で実際に表示されるPOIシンボルとOSM tagの対応。
- [Google Material Icons](https://developers.google.com/fonts/docs/material_icons): Android・iOS・Webで利用できる汎用SVG/PNG素材と小サイズ向け設計。Apache-2.0。

## 推奨するプロダクト構造

300種類を300個の独立したマーカーclassとして実装せず、glyphとcontainerを分離する。

| レイヤー | 候補 | 用途 |
|---|---|---|
| Glyph | `MapIconGlyph.hospital` など | 単色・小サイズで判別できる意味記号 |
| Pin container | `PinGlyphIcon` | デフォルトピン内にglyphを表示 |
| Circle container | `CircleGlyphIcon` | 円形badge内にglyphを表示 |
| Rounded rectangle | `RoundedImageIcon` | 写真・ロゴを角丸長方形へcropして表示 |
| Bubble container | `InfoBubbleIcon` | glyph/写真とlabel/snippetを組み合わせる |
| Status decoration | selected、warning、cluster countなど | 枠、badge、色で状態を追加 |

glyphは原則として単色maskにし、fill/stroke/backgroundはcontainer側で指定する。これによりAndroid VectorDrawable、iOS CGPath/SF Symbols fallback、React SVGで同じIDを共有しやすい。

## 300 glyph候補

### 医療・健康・緊急 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 1 | `hospital` | 病院 | 病院＋十字 |
| 2 | `clinic` | 診療所 | 小型施設＋十字 |
| 3 | `doctor` | 医師 | 聴診器 |
| 4 | `dentist` | 歯科 | 歯 |
| 5 | `pharmacy` | 薬局 | 乳鉢・薬 |
| 6 | `medical_lab` | 医療検査所 | フラスコ |
| 7 | `blood_bank` | 血液センター | 血液滴 |
| 8 | `ambulance` | 救急車 | 救急車両 |
| 9 | `emergency_room` | 救急外来 | ER＋十字 |
| 10 | `first_aid` | 応急処置 | 救急箱 |
| 11 | `defibrillator` | AED | 心臓＋稲妻 |
| 12 | `veterinary` | 動物病院 | 肉球＋十字 |
| 13 | `nursing_home` | 介護施設 | 人＋ハート |
| 14 | `hospice` | ホスピス | 家＋ハート |
| 15 | `physiotherapist` | 理学療法 | 歩行する人 |
| 16 | `chiropractor` | 整体・カイロ | 背骨 |
| 17 | `optician` | 眼科・眼鏡店 | 眼鏡 |
| 18 | `mental_health` | メンタルヘルス | 頭部＋ハート |
| 19 | `maternity` | 産科・助産 | 親子 |
| 20 | `vaccination` | 予防接種 | 注射器 |

### 飲食 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 21 | `restaurant` | レストラン | フォーク＋ナイフ |
| 22 | `cafe` | カフェ | コーヒーカップ |
| 23 | `fast_food` | ファストフード | 持ち帰り袋 |
| 24 | `bar` | バー | カクテルグラス |
| 25 | `pub` | パブ | ビールジョッキ |
| 26 | `bakery` | パン屋 | パン |
| 27 | `ice_cream` | アイスクリーム店 | アイスクリーム |
| 28 | `pizza` | ピザ店 | ピザ |
| 29 | `burger` | ハンバーガー店 | バーガー |
| 30 | `sushi` | 寿司店 | 寿司 |
| 31 | `ramen` | ラーメン店 | 麺鉢 |
| 32 | `noodles` | 麺料理店 | 麺＋箸 |
| 33 | `seafood` | シーフード店 | 魚 |
| 34 | `barbecue` | 焼肉・BBQ | グリル |
| 35 | `vegetarian` | ベジタリアン料理 | 葉 |
| 36 | `halal_food` | ハラール料理 | 認証マーク風 |
| 37 | `food_court` | フードコート | 複数の食器 |
| 38 | `grocery` | 食料品店 | 買い物かご＋食品 |
| 39 | `convenience_store` | コンビニ | 店舗＋かご |
| 40 | `drinking_water` | 飲料水 | 蛇口＋水滴 |

### 買い物 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 41 | `shopping_mall` | ショッピングモール | 買い物袋＋建物 |
| 42 | `department_store` | 百貨店 | 多層店舗 |
| 43 | `market` | 市場 | 屋台 |
| 44 | `farmers_market` | ファーマーズマーケット | 屋台＋野菜 |
| 45 | `clothing_store` | 衣料品店 | シャツ |
| 46 | `shoe_store` | 靴店 | 靴 |
| 47 | `jewelry_store` | 宝飾店 | 宝石 |
| 48 | `electronics_store` | 家電店 | 画面＋プラグ |
| 49 | `mobile_phone_store` | 携帯電話店 | スマートフォン |
| 50 | `book_store` | 書店 | 本 |
| 51 | `gift_shop` | ギフト店 | プレゼント |
| 52 | `toy_store` | 玩具店 | 積み木 |
| 53 | `sporting_goods_store` | スポーツ用品店 | ボール＋バッグ |
| 54 | `furniture_store` | 家具店 | 椅子 |
| 55 | `hardware_store` | ホームセンター | ハンマー |
| 56 | `garden_center` | 園芸店 | 鉢植え |
| 57 | `florist` | 花店 | 花 |
| 58 | `cosmetics_store` | 化粧品店 | 化粧ボトル |
| 59 | `pet_store` | ペット店 | 肉球 |
| 60 | `thrift_store` | リユース店 | ハンガー＋循環矢印 |

### 公共交通 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 61 | `airport` | 空港 | 旅客機 |
| 62 | `airfield` | 飛行場 | 小型機 |
| 63 | `heliport` | ヘリポート | ヘリコプター |
| 64 | `train_station` | 鉄道駅 | 列車正面 |
| 65 | `subway_station` | 地下鉄駅 | 地下鉄車両 |
| 66 | `light_rail_station` | ライトレール駅 | LRT車両 |
| 67 | `tram_stop` | 路面電車停留所 | トラム |
| 68 | `bus_station` | バスターミナル | バス＋建物 |
| 69 | `bus_stop` | バス停 | バス＋標識 |
| 70 | `ferry_terminal` | フェリーターミナル | フェリー |
| 71 | `harbor` | 港 | 錨 |
| 72 | `taxi_stand` | タクシー乗り場 | タクシー |
| 73 | `rideshare` | ライドシェア乗降場 | 車＋利用者 |
| 74 | `bike_share` | シェアサイクル | 自転車＋循環矢印 |
| 75 | `scooter_share` | シェアスクーター | キックボード |
| 76 | `cable_car` | ロープウェイ | ゴンドラ |
| 77 | `funicular` | ケーブルカー | 傾斜列車 |
| 78 | `monorail` | モノレール | 高架列車 |
| 79 | `park_and_ride` | パーク＆ライド | P＋交通機関 |
| 80 | `ticket_office` | 乗車券売場 | 切符 |

### 自動車・移動・物流 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 81 | `parking` | 駐車場 | P |
| 82 | `parking_garage` | 立体駐車場 | P＋建物 |
| 83 | `accessible_parking` | 障害者用駐車場 | P＋車椅子 |
| 84 | `ev_charging` | EV充電所 | 車＋プラグ |
| 85 | `ebike_charging` | 電動自転車充電所 | 自転車＋プラグ |
| 86 | `gas_station` | ガソリンスタンド | 給油機 |
| 87 | `car_rental` | レンタカー | 車＋鍵 |
| 88 | `car_dealer` | 自動車販売店 | 車＋値札 |
| 89 | `car_repair` | 自動車修理 | 車＋レンチ |
| 90 | `car_wash` | 洗車場 | 車＋水滴 |
| 91 | `tire_shop` | タイヤ店 | タイヤ |
| 92 | `motorcycle` | オートバイ関連 | オートバイ |
| 93 | `bicycle_parking` | 駐輪場 | 自転車＋P |
| 94 | `bicycle_repair` | 自転車修理 | 自転車＋レンチ |
| 95 | `rest_stop` | 休憩所・SA/PA | ベンチ＋道路 |
| 96 | `toll_station` | 料金所 | ゲート＋料金 |
| 97 | `border_control` | 国境検査所 | 検査ゲート |
| 98 | `weigh_station` | 車両計量所 | トラック＋秤 |
| 99 | `truck_stop` | トラック休憩所 | トラック＋P |
| 100 | `driving_school` | 自動車教習所 | 車＋学習者 |

### 宿泊・住居 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 101 | `hotel` | ホテル | ベッド＋建物 |
| 102 | `motel` | モーテル | ベッド＋車 |
| 103 | `hostel` | ホステル | 二段ベッド |
| 104 | `guest_house` | ゲストハウス | 家＋ベッド |
| 105 | `bed_and_breakfast` | B&B | ベッド＋朝食 |
| 106 | `resort` | リゾートホテル | ホテル＋太陽 |
| 107 | `ryokan` | 旅館 | 和風建物＋布団 |
| 108 | `minshuku` | 民宿 | 家＋布団 |
| 109 | `capsule_hotel` | カプセルホテル | カプセルベッド |
| 110 | `campground` | キャンプ場 | テント |
| 111 | `cabin` | キャビン・山小屋 | 丸太小屋 |
| 112 | `cottage` | コテージ | 小さな家 |
| 113 | `rv_park` | RVパーク | キャンピングカー |
| 114 | `apartment` | 集合住宅 | マンション |
| 115 | `dormitory` | 寮 | 建物＋複数ベッド |
| 116 | `emergency_shelter` | 避難所 | 家＋盾 |
| 117 | `refugee_center` | 難民支援施設 | 家＋人々 |
| 118 | `vacation_rental` | 貸別荘 | 家＋スーツケース |
| 119 | `houseboat` | ハウスボート | 家＋船 |
| 120 | `farmstay` | 農家民宿 | 家＋納屋 |

### 観光・文化・娯楽 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 121 | `museum` | 博物館 | 列柱建物 |
| 122 | `art_gallery` | 美術館・ギャラリー | 額縁 |
| 123 | `theater` | 劇場 | 舞台マスク |
| 124 | `cinema` | 映画館 | 映写機 |
| 125 | `concert_hall` | コンサートホール | 音符＋建物 |
| 126 | `opera_house` | オペラハウス | 舞台＋音符 |
| 127 | `monument` | 記念碑 | オベリスク |
| 128 | `memorial` | 慰霊碑 | 記念プレート |
| 129 | `castle` | 城 | 城郭 |
| 130 | `ruins` | 遺跡 | 崩れた柱 |
| 131 | `archaeological_site` | 考古学遺跡 | 土器・発掘 |
| 132 | `historic_site` | 史跡 | 歴史的建物 |
| 133 | `landmark` | ランドマーク | 星＋建物 |
| 134 | `sculpture` | 彫刻 | 彫像 |
| 135 | `fountain` | 噴水 | 噴水 |
| 136 | `planetarium` | プラネタリウム | ドーム＋星 |
| 137 | `aquarium` | 水族館 | 魚＋水槽 |
| 138 | `zoo` | 動物園 | 動物の顔 |
| 139 | `tourist_attraction` | 観光名所 | カメラ＋星 |
| 140 | `observation_deck` | 展望台 | 双眼鏡 |

### 自然・アウトドア (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 141 | `park` | 公園 | 木＋ベンチ |
| 142 | `national_park` | 国立公園 | 山＋木 |
| 143 | `garden` | 庭園 | 花＋小道 |
| 144 | `botanical_garden` | 植物園 | 植物＋温室 |
| 145 | `forest` | 森林 | 複数の木 |
| 146 | `mountain` | 山・山頂 | 山 |
| 147 | `volcano` | 火山 | 噴煙のある山 |
| 148 | `hill` | 丘 | なだらかな丘 |
| 149 | `viewpoint` | 展望地点 | 双眼鏡＋景観 |
| 150 | `waterfall` | 滝 | 崖＋水 |
| 151 | `river` | 河川 | 蛇行する水 |
| 152 | `lake` | 湖 | 水面＋岸 |
| 153 | `beach` | 海岸・ビーチ | 波＋パラソル |
| 154 | `island` | 島 | 島＋水 |
| 155 | `cave` | 洞窟 | 洞窟入口 |
| 156 | `hot_spring` | 温泉 | 湯気 |
| 157 | `picnic_area` | ピクニック場 | テーブル |
| 158 | `trailhead` | 登山口 | 道標＋道 |
| 159 | `desert` | 砂漠 | 砂丘＋太陽 |
| 160 | `wildlife_refuge` | 野生動物保護区 | 鳥＋盾 |

### スポーツ (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 161 | `stadium` | スタジアム | 競技場 |
| 162 | `arena` | アリーナ | 屋内競技場 |
| 163 | `sports_center` | スポーツセンター | 複数ボール |
| 164 | `gym` | ジム | ダンベル |
| 165 | `fitness_center` | フィットネス施設 | 運動する人 |
| 166 | `swimming_pool` | プール | 泳ぐ人 |
| 167 | `tennis_court` | テニスコート | ラケット＋ボール |
| 168 | `golf_course` | ゴルフ場 | 旗＋ボール |
| 169 | `mini_golf` | ミニゴルフ | 小型パター |
| 170 | `baseball` | 野球場 | バット＋ボール |
| 171 | `basketball` | バスケットボール場 | バスケットボール |
| 172 | `soccer` | サッカー場 | サッカーボール |
| 173 | `american_football` | アメフト場 | 楕円球 |
| 174 | `rugby` | ラグビー場 | ラグビーボール |
| 175 | `volleyball` | バレーボール場 | バレーボール |
| 176 | `skatepark` | スケートパーク | スケートボード |
| 177 | `ski_resort` | スキー場 | スキーヤー |
| 178 | `ice_rink` | スケートリンク | スケート靴 |
| 179 | `bowling` | ボウリング場 | ピン＋ボール |
| 180 | `fishing` | 釣り場 | 釣り針＋魚 |

### 教育・行政・公共 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 181 | `preschool` | 保育園・幼稚園 | 幼児＋積み木 |
| 182 | `primary_school` | 小学校 | 校舎＋児童 |
| 183 | `secondary_school` | 中学・高校 | 校舎＋本 |
| 184 | `college` | 短大・専門学校 | 卒業帽 |
| 185 | `university` | 大学 | 大学建物 |
| 186 | `research_institute` | 研究機関 | 顕微鏡 |
| 187 | `library` | 図書館 | 開いた本＋建物 |
| 188 | `community_center` | コミュニティセンター | 人々＋建物 |
| 189 | `city_hall` | 市役所 | 行政建物 |
| 190 | `government_office` | 官公庁 | 旗＋建物 |
| 191 | `courthouse` | 裁判所 | 天秤＋建物 |
| 192 | `embassy` | 大使館・領事館 | 旗 |
| 193 | `police_station` | 警察署 | 警察バッジ |
| 194 | `fire_station` | 消防署 | 消防ヘルメット |
| 195 | `post_office` | 郵便局 | 封筒＋建物 |
| 196 | `prison` | 刑務所 | 格子 |
| 197 | `ranger_station` | レンジャー事務所 | 森林バッジ |
| 198 | `convention_center` | コンベンションセンター | 会議場 |
| 199 | `polling_station` | 投票所 | 投票箱 |
| 200 | `military_base` | 軍事施設 | 盾＋星 |

### 金融・ビジネス (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 201 | `bank` | 銀行 | 銀行建物 |
| 202 | `atm` | ATM | カード＋現金 |
| 203 | `currency_exchange` | 両替所 | 循環矢印＋通貨 |
| 204 | `accounting` | 会計事務所 | 電卓 |
| 205 | `insurance` | 保険代理店 | 傘・盾 |
| 206 | `corporate_office` | 企業オフィス | オフィスビル |
| 207 | `business_center` | ビジネスセンター | ブリーフケース |
| 208 | `coworking` | コワーキングスペース | 机＋人々 |
| 209 | `factory` | 工場 | 煙突付き工場 |
| 210 | `warehouse` | 倉庫 | 箱＋倉庫 |
| 211 | `farm` | 農場 | 納屋 |
| 212 | `ranch` | 牧場 | 牛＋柵 |
| 213 | `television_studio` | テレビ局 | テレビカメラ |
| 214 | `newspaper` | 新聞社 | 新聞 |
| 215 | `supplier` | 資材供給業者 | 箱＋矢印 |
| 216 | `wholesale` | 卸売市場 | 複数の箱 |
| 217 | `trade_center` | 交易・展示センター | 握手＋建物 |
| 218 | `startup_hub` | スタートアップ拠点 | ロケット＋建物 |
| 219 | `data_center` | データセンター | サーバー |
| 220 | `stock_exchange` | 証券取引所 | チャート＋建物 |

### 生活・専門サービス (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 221 | `barber` | 理容室 | 理容ポール |
| 222 | `hair_salon` | 美容室 | はさみ＋髪 |
| 223 | `beauty_salon` | ビューティーサロン | 鏡 |
| 224 | `spa` | スパ | 蓮＋水 |
| 225 | `massage` | マッサージ | 手 |
| 226 | `laundry` | コインランドリー | 洗濯機 |
| 227 | `dry_cleaning` | クリーニング店 | ハンガー |
| 228 | `tailor` | 仕立て屋 | 針＋糸 |
| 229 | `locksmith` | 鍵屋 | 鍵 |
| 230 | `plumber` | 配管業者 | パイプレンチ |
| 231 | `electrician` | 電気工事 | プラグ＋稲妻 |
| 232 | `painter` | 塗装業者 | ペイントローラー |
| 233 | `moving_company` | 引越業者 | 箱＋トラック |
| 234 | `courier` | 宅配・配送 | 小包＋走者 |
| 235 | `storage` | トランクルーム | 箱＋倉庫扉 |
| 236 | `lawyer` | 法律事務所 | 天秤＋書類 |
| 237 | `consultant` | コンサルタント | 人物＋グラフ |
| 238 | `employment_agency` | 人材紹介 | 人物＋鞄 |
| 239 | `travel_agency` | 旅行代理店 | 地球＋スーツケース |
| 240 | `tourist_information` | 観光案内所 | i＋地図 |

### 宗教・地域福祉 (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 241 | `church` | 教会 | 十字架付き教会 |
| 242 | `cathedral` | 大聖堂 | 尖塔 |
| 243 | `chapel` | 礼拝堂 | 小型教会 |
| 244 | `mosque` | モスク | ドーム＋三日月 |
| 245 | `synagogue` | シナゴーグ | 六芒星＋建物 |
| 246 | `hindu_temple` | ヒンドゥー寺院 | 寺院塔 |
| 247 | `buddhist_temple` | 仏教寺院 | 仏塔 |
| 248 | `shinto_shrine` | 神社 | 鳥居 |
| 249 | `sikh_gurdwara` | グルドワラ | 宗教建築 |
| 250 | `jain_temple` | ジャイナ教寺院 | 寺院シンボル |
| 251 | `monastery` | 修道院 | 中庭付き宗教建築 |
| 252 | `cemetery` | 墓地 | 墓石 |
| 253 | `crematorium` | 火葬場 | 炎＋建物 |
| 254 | `social_facility` | 福祉施設 | 人々＋ハート |
| 255 | `childcare` | 託児施設 | 大人＋子ども |
| 256 | `animal_shelter` | 動物保護施設 | 家＋肉球 |
| 257 | `food_bank` | フードバンク | 箱＋食品 |
| 258 | `charity` | 慈善団体 | 手＋ハート |
| 259 | `public_bookcase` | 公共本棚 | 本棚 |
| 260 | `community_kitchen` | 地域食堂 | 人々＋食器 |

### 設備・アクセシビリティ (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 261 | `toilet` | トイレ | 男女・共用トイレ |
| 262 | `accessible_toilet` | 多目的トイレ | 車椅子＋トイレ |
| 263 | `shower` | シャワー | シャワーヘッド |
| 264 | `water_refill` | 給水スポット | ボトル＋水滴 |
| 265 | `bench` | ベンチ | ベンチ |
| 266 | `covered_shelter` | 東屋・待避所 | 屋根＋ベンチ |
| 267 | `public_phone` | 公衆電話 | 受話器 |
| 268 | `wifi` | 公衆Wi-Fi | Wi-Fi波形 |
| 269 | `device_charging` | 端末充電 | 電池＋プラグ |
| 270 | `information` | 案内所 | i |
| 271 | `elevator` | エレベーター | 上下矢印＋人 |
| 272 | `stairs` | 階段 | 階段 |
| 273 | `escalator` | エスカレーター | 移動階段 |
| 274 | `entrance` | 入口 | 扉＋入る矢印 |
| 275 | `exit` | 出口 | 扉＋出る矢印 |
| 276 | `wheelchair_access` | 車椅子対応 | 車椅子 |
| 277 | `hearing_loop` | 聴覚支援 | 耳＋T |
| 278 | `baby_changing` | おむつ交換台 | 乳児＋台 |
| 279 | `locker` | ロッカー | 鍵付き収納 |
| 280 | `recycling` | リサイクル | 循環矢印 |

### 安全・道路・インフラ (20)

| No. | ID | 日本語名 | glyphの造形案 |
|---:|---|---|---|
| 281 | `road_closed` | 通行止め | バリケード |
| 282 | `construction` | 工事中 | 工事ヘルメット |
| 283 | `warning` | 注意地点 | 警告三角形 |
| 284 | `traffic_accident` | 交通事故 | 衝突する車 |
| 285 | `speed_camera` | 速度取締カメラ | カメラ＋速度 |
| 286 | `traffic_signal` | 信号機 | 三色信号 |
| 287 | `stop_sign` | 一時停止 | 八角形標識 |
| 288 | `railway_crossing` | 踏切 | 列車＋交差 |
| 289 | `bridge` | 橋 | 橋梁 |
| 290 | `tunnel` | トンネル | トンネル入口 |
| 291 | `dam` | ダム | 堰＋水 |
| 292 | `lighthouse` | 灯台 | 灯台＋光 |
| 293 | `communications_tower` | 通信塔 | 塔＋電波 |
| 294 | `wind_turbine` | 風力発電 | 風車 |
| 295 | `power_plant` | 発電所 | 稲妻＋工場 |
| 296 | `fire_hydrant` | 消火栓 | 消火栓 |
| 297 | `emergency_phone` | 非常電話 | SOS＋受話器 |
| 298 | `assembly_point` | 避難集合場所 | 集まる矢印 |
| 299 | `current_location` | 現在地 | 照準＋現在地 |
| 300 | `selected_location` | 選択地点 | 強調ピン |

## 実装前に決める事項

1. IDは3プラットフォームで完全一致させ、表示名はローカライズ資源へ分離する。
2. 15–16pxでも潰れないfilled glyphを基準にし、線幅や細部を増やしすぎない。
3. 宗教・通貨・交通標識など地域差が大きいglyphは、文化的中立性と地域別variantをレビューする。
4. Maki/TemakiのCC0素材は再利用候補、Material Icons由来素材はApache-2.0 noticeを管理する。Google Placesの配信iconはAPI利用規約下にあるため、そのまま同梱しない。
5. 写真系はglyph catalogとは別APIにし、aspect ratio、crop、corner radius、border、shadow、placeholderを指定可能にする。
6. 最初のリリースでは頻度の高い60–100 glyphを実装し、残りは同じID体系で段階追加する。
