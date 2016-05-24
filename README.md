### 封装Glide的图片加载库和图片查看库
![加载图](http://7xpp4m.com1.z0.glb.clouddn.com/loadImage.gif)

![查看大图](http://7xpp4m.com1.z0.glb.clouddn.com/final.gif)

### 加载图片
	GlideImageLoader.getInstance().loadImage(...);

### 查看图片大图
	
	Intent intent = new Intent(this, BigPhotoActivity.class);
	        ArrayList<String> urls = new ArrayList<>();
	        urls.add("http://ww3.sinaimg.cn/mw690/005Fj2RDgw1f3tg4dkfwjj30c846kwy6.jpg");
	        urls.add("http://ww1.sinaimg.cn/large/7a8aed7bgw1f3j8jt6qn8j20vr15owvk.jpg");
	        urls.add("http://ww3.sinaimg.cn/mw690/8345c393jw1f32xv7zd4gj20go24yaqv.jpg");
	        urls.add("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3c7zc3y3rj20rt15odmp.jpg");
	
	        intent.putStringArrayListExtra(Common.BigPhotoActivity.URLS, urls);
	        intent.putExtra(Common.BigPhotoActivity.CURRENT_POSITION, 0);//当前图片的位置
	        startActivity(intent);
	        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);