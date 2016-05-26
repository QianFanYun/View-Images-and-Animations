### 封装Glide和PhotoView的图片加载库和图片查看库
![加载图](http://7xpp4m.com1.z0.glb.clouddn.com/loadImage.gif)

![查看大图](http://7xpp4m.com1.z0.glb.clouddn.com/viewpager.gif)

### 加载图片
    加载普通图片
	GlideImageLoader.getInstance().loadImage(...);
	加载圆形图片
	GlideImageLoader.getInstance().loadCircleImage(...);
	加载圆角图片
	GlideImageLoader.getInstance().loadRoundImage(...,int dp);
	添加加载监听
	GlideImageLoader.getInstance().loadImage(...,LoadingImageListener loadListener);
	加载Gif图
	GlideImageLoader.getInstance().loadGif(...);

### 查看图片大图
	
	Intent intent = new Intent(this, BigPhotoActivity.class);
        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://ww2.sinaimg.cn/large/610dc034jw1f454lcdekoj20dw0kumzj.jpg");
        urls.add("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png");
        urls.add("http://ww3.sinaimg.cn/mw690/8345c393jw1f32xv7zd4gj20go24yaqv.jpg");
        urls.add("http://ww2.sinaimg.cn/large/7a8aed7bjw1f3c7zc3y3rj20rt15odmp.jpg");

        intent.putStringArrayListExtra(Common.BigPhotoActivity.URLS, urls);
        intent.putExtra(Common.BigPhotoActivity.CURRENT_POSITION, 0);
        startActivity(intent);
        overridePendingTransition(R.anim.photo_activity_in,0);

### PhotoView原理解析

[PhotoView源码解析](https://www.zybuluo.com/archeryc/note/374243)