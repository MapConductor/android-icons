# Global Icon Collection Roadmap

更新日: 2026-08-14

## 成功条件

各国のアプリ開発者が、一般的なPOIは共通コレクションから、地域固有の記号は自国パックから明示的に選べる状態を作る。端末Localeや現在地による自動置換は行わない。

国別パックは共通約300glyphの複製ではない。郵便局、警察、道路標識、公共施設、宗教・文化施設など、形または意味が地域によって変わるものだけを収録する「差分パック」とする。

## 初期ライン

| Pack | 種別 | 初期glyph | 役割 |
|---|---|---:|---|
| `icons` | common | 1（候補300） | 世界共通のPOIと全container |
| `icons-jp` | country | 3 | 日本の郵便局、交番、神社 |
| `icons-us` | country | 3 | 米国の郵便局、警察、Interstate |
| `icons-weather` | theme | 7 | 晴れ、曇り、雨、雪、雷、風、霧 |

初期実装はAPIと制作工程を確立するための最小セットであり、完成数ではない。次の制作単位はcommonを20〜30件ずつ、country packを5〜10件ずつとする。

## 世界展開の順序

国の優先順位を固定的な「重要度」として扱わない。次の情報を組み合わせ、四半期ごとに追加対象を選ぶ。

1. MapConductor利用者・導入候補からの要望
2. 共通glyphでは誤認される地域固有記号の多さ
3. 現地開発者またはデザイナーによるレビュー体制
4. 公開統計・地図分類から確認できる利用頻度
5. 商標、政府標章、緊急標章などの法的確認可能性

準備候補は`gb`、`ca`、`au`、`de`、`fr`、`br`、`mx`、`in`、`kr`、`cn`、`tw`、`sg`、`th`、`id`、`za`などとするが、現地レビューなしに「その国の標準」と断定して公開しない。

## パック追加契約

- country pack名はISO 3166-1 alpha-2小文字を基本とする。
- theme packは`weather`のような意味名を使い、国コードと混同しない。
- IDは`jp.post_office`、`us.interstate`、`weather.rain`のようにpack namespaceを含める。
- country packは共通`MapIconGlyph`へ依存し、独自rendererやcacheを持たない。
- 共通IDを暗黙に上書きしない。開発者がどちらを使うか選ぶ。
- 1つの正本からAndroid、Swift、TypeScriptを生成し、形状差分をテストする。

## 現地レビュー

各country packには、少なくとも次のレビュー情報を残す。

- どの施設・制度・標識を表すか
- 全国的、州・県限定、事業者限定のどれか
- 現地で別の意味に読まれないか
- 色を外して単色glyphにしても意味が残るか
- 政府標章、企業ロゴ、保護標章の複製になっていないか
- 共通版を使うべきケースとcountry版を使うべきケース

## プラットフォーム対応

新しいpackは常に次の3成果物を同じID集合で提供する。

| Platform | 命名例 |
|---|---|
| Android | `com.mapconductor:icons-jp` |
| iOS | `MapConductorIconsJP` / `ios-icons-jp` |
| React | `@mapconductor/react-icons-jp` |

いずれか1プラットフォームだけ先行公開する場合も、registryで状態を明記し、同じIDに別の意味を割り当てない。
