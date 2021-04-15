# Android-TextFileWriteRead

## 書いていること
- Android 内部ストレージにテキストファイルを保存する工程
- Android 外部ストレージにテキストファイルを保存する工程
- Google Speech?での入力と受け取り
- Retrofitを使用してGET通信
- Retrofitを使用してPOST通信
- RetrofitをAsyncTaskで実行しての通信
- Dialogを立ち上げて音声入力　データをサーバーに送信　結果を取得

---
#### File書き込みに関して
[お試しサンプル](https://qiita.com/f-paico/items/4a4457483e53aa39006c)  
URLを保存し忘れてた 泣

---
#### 音声入力　文字起こし

[良サンプル](https://akira-watson.com/android/recognizerintent.html)    
[良良サンプル](https://android-java.hatenablog.jp/entry/2018/03/27/115126)

---

#### Retrofit Sample  
[Gradleについて　Codeで入れる方法](https://qiita.com/190yamashita/items/f00d8b2f908dd754c506)  
[Kotlin sample](https://101010.fun/programming/android-try-retrofit.html)  
[良サンプル](https://qiita.com/joji/items/81f44b75f1d267fef4fe)  
[Kotlin 良サンプル](https://qiita.com/naoi/items/5036adc8d33638911deb)  
[error 対策　Expected BEGIN_OBJECT but was BEGIN_ARRAY](https://qiita.com/hisakioomae/items/b185e5a2d685d4ed25f8)  

---
#### AsyncTaskの使用
[わかりやすいメソッド説明](https://dev.classmethod.jp/articles/asynctask/)  
[AsyncTaskの引数について](https://sites.google.com/site/technoute/android/thread/params)  
[良サンプル](https://qiita.com/furusin_oriver/items/59dd0ae6dc795737eded)  
??client??
[わからないけど製品に近いサンプル](https://qiita.com/apukasukabian/items/0de8cc99d34ad2ac89c2)  
```json
{
  "count":"count"
}
```
##### AsyncTask Post
[良サンプル](https://qiita.com/shts/items/775973783966ce7b19cf)  
[ApiService　書き方](http://pppurple.hatenablog.com/entry/2018/06/30/234400#POSTjson)  
[PHPでの簡易API](https://blog.takady.net/blog/2017/10/28/php_get_json_from_post_request_body/)  
```php
$params = json_decode(file_get_contents('php://input'), true);
echo json_encode($params);
```

---
#### Dialogについて
[良サンプル on kotlin 全体的に存在](https://eh-career.com/engineerhub/entry/2020/04/02/103000)  
[良サンプル on java](https://qiita.com/suzukihr/items/8973527ebb8bb35f6bb8)  
[公式サンプル](https://developer.android.com/guide/topics/ui/dialogs.html#java)  
[公式サンプル　AlertDialog](https://developer.android.com/reference/android/app/AlertDialog.Builder.html)  
[dialogfragment viewにbtnの処理を足したい時など](https://stackoverflow.com/questions/27770316/view-onclicklistener-in-a-custom-dialogfragment/27771830)  
[Activityの終了時処理などイベントの発生](https://developer.android.com/training/basics/intents/result?hl=ja)  
[startActivityForResultについて](https://araramistudio.jimdo.com/2018/02/15/android%E3%81%A7activity%E3%81%8B%E3%82%89%E7%B5%90%E6%9E%9C%E3%82%92%E5%8F%97%E3%81%91%E5%8F%96%E3%82%8B/)  
[HTTP通信をしたい時](https://qiita.com/superman9387/items/7441998138a8509537a4)  
[非同期処理の中で非同期処理をする必要はないようね](https://qiita.com/furusin_oriver/items/59dd0ae6dc795737eded)  

---
