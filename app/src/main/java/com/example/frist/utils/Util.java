package com.example.frist.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Util {
	/**
	 * 屏幕的宽度
	 */
	private static int deviceWidth = 0;

	/**
	 * 屏幕的高度
	 */
	private static int deviceHeigt = 0;

	/**
	 * dialog id:数据载入中的对话框
	 */
	public static final int DLG_ID_DATA_LOADING = 0;

	/**
	 * 关闭显示的对话框
	 */
	public static final int DLG_COLSE = 1;

	/**
	 * The Common Dialog.
	 */
	private static Dialog mDialog;

	private static float density;

	private OnClickTwoBtnDialogListener twoBtnDialogListener;

	private static SimpleDateFormat dateFormat;

	static {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		dateFormat.setTimeZone(TimeZone.getDefault());
	}

	public static SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * 确认是否打开飞行模式.
	 *
	 * @return true: 开, false: 关
	 */
	public static boolean isAirPlaneModeOn(Context context) {
		return Settings.System.getInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 0) != 0;
	}

	/**
	 * 确认网络是否打开.
	 *
	 * @return true: 开, false: 关
	 */
	public static boolean isNetworkActive(Context context) {
		if (isAirPlaneModeOn(context)) {
			return false;
		}
		ConnectivityManager conManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		}
		return false;
	}

	/**
	 * 关闭对话框
	 */
	public synchronized static void closeDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
			mDialog.cancel();
			mDialog = null;
		}
	}
	public Dialog getProgressDialog() {
		return mDialog;
	}

	/**
	 * 获得屏幕的宽度
	 *
	 * @return 屏幕的宽度
	 */
	public static int getDeviceWidth() {
		return deviceWidth;
	}

	/**
	 * 获得屏幕的高度
	 *
	 * @return
	 */
	public static int getDeviceHeight() {
		return deviceHeigt;
	}

	/**
	 * 设置屏幕的尺寸 当用getDeviceWidth()或者getDeviceHeight()的结果为0时，调用此方法，然后再获得屏幕的尺寸
	 */
	public static void setDeviceWidthHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		deviceWidth = dm.widthPixels;
		deviceHeigt = dm.heightPixels;
		density = activity.getResources().getDisplayMetrics().density;
	}

	public static float getDeviceDesity() {
		return density;
	}

	public static boolean isWifiConnected(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		// 如果wifi等网络状态是连接的，则退出，否则显示提示信息进入网络设置界面
		if (wifi == State.CONNECTED || wifi == State.CONNECTING)
			return true;
		return false;
	}

	/**
	 * 震动
	 *
	 * @param context
	 */
	public static void vibratorBegin(Context context) {
		Vibrator vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 800, 500 }; // OFF/ON/OFF/ON...
		vibrator.vibrate(pattern, -1);
	}

	public static void musicBegin(Context context) {
		Uri uri = Uri.parse(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI);
		RingtoneManager.setActualDefaultRingtoneUri(context,
				RingtoneManager.TYPE_RINGTONE, uri);
	}

	public static void ring(Context context) {
		Uri alert = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		final MediaPlayer player = new MediaPlayer();
		try {
			player.setDataSource(context, alert);
			player.prepare();
			player.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			player.release();
		}
	}

	/**
	 * 生成指定的对话框
	 */
	public static Dialog onCreateDialog(int id, Context context, String toast) {
		Dialog result = null;
		Dialog dialog = null;
		switch (id) {
		case DLG_ID_DATA_LOADING:

			break;
		}
		return result;
	}

	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		if (bitmap == null)
			return null;
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static Bitmap getDrawableResouce2Bitmap(Context c, int id) {
		Drawable drawable = c.getResources().getDrawable(id);
		BitmapDrawable bd = (BitmapDrawable) drawable;
		return bd.getBitmap();
	}

	/**
	 * 加载本地图片
	 *
	 * @param url
	 * @return
	 */
	public static Bitmap getLocalBitmap(String url) {
		FileInputStream fis = null;
		try {
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inPreferredConfig = Config.RGB_565;
			opt.inPurgeable = true;
			opt.inInputShareable = true;
			fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis, null, opt); // /把流转化为Bitmap图片
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}






	public static BitmapFactory.Options getLocalBitmapSize(String url) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(url, opts);
		return opts;
	}

	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	public static String sha1(String s) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();
			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toHexString(byte[] keyData) {
		if (keyData == null) {
			return null;
		}
		int expectedStringLen = keyData.length * 2;
		StringBuilder sb = new StringBuilder(expectedStringLen);
		for (int i = 0; i < keyData.length; i++) {
			String hexStr = Integer.toString(keyData[i] & 0x00FF, 16);
			if (hexStr.length() == 1) {
				sb.append("0").append(hexStr);
			} else {
				sb.append(hexStr);
			}
		}
		return sb.toString();
	}

	/**
	 * 判断是不是手机号
	 * @param mobiles
	 * @return
     */
	public static boolean isMobileNO(String mobiles) {
		// String regEx = "^[1][358][0-9]{9}$";
		Pattern p = Pattern.compile("^[\\d]{11}$");
		// Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(mobiles);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是不是email
	 * @param
	 * @return
	 */
	public static boolean isMail(String mails) {
		String regEx = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(mails);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断有没有特殊字符
	 * @param
	 * @return
	 */
	public static boolean hasSpecialCharacter(String str) {
		String regEx = "^[a-zA-Z]+[a-zA-Z0-9_]*$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return false;
		}
		return true;
	}

	/**
	 * 两数相乘
	 *
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static float twofloatResult(float f1, float f2) {
		int m = 0;
		String s1 = "" + f1;
		String s2 = "" + f2;
		m += s1.split("\\.")[1].length();
		m += s2.split("\\.")[1].length();
		return (float) (Float.parseFloat(s1.replace(".", ""))
				* Float.parseFloat(s2.replace(".", "")) / Math.pow(10, m));
	}


	public OnClickTwoBtnDialogListener getTwoBtnDialogListener() {
		return twoBtnDialogListener;
	}

	public void setTwoBtnDialogListener(OnClickTwoBtnDialogListener listener) {
		twoBtnDialogListener = listener;
	}

	public interface OnClickTwoBtnDialogListener {
		public void onClickBtn1();

		public void onClickBtn2();
	}

	/**
	 * 获取androidSDK的版
	 * @param
	 * @return
	 */
	public static int getAndroidSDKVersion() {
		int version = 0;
		try {
			version = Integer.valueOf(Build.VERSION.SDK);
		} catch (NumberFormatException e) {
		}
		return version;
	}

	/**
	 * 计算剩余时间
	 *
	 * @param nowTime
	 *            现在的时间
	 * @param endTime
	 *            截止时间
	 * @return
	 */
	public static String remainingTime(int nowTime, int endTime) {
		String strTime = "";
		Date endTimeDate = new Date(endTime);
		Date nowTimeDate = new Date(nowTime);
		long time = endTimeDate.getTime() - nowTimeDate.getTime();
		long days = time / (60 * 60 * 24);
		long hour = (time / (60 * 60) - days * 24);
		long min = ((time / 60) - days * 24 * 60 - hour * 60);
		strTime = days + "天" + hour + "小时" + min + "分";
		return strTime;
	}

	/**
	 *判断sd是否存在
	 * @return
	 */
	public static boolean existSDCard() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 *
	 * @param filePath
	 * @return
	 */
	public static byte[] file2byte(String filePath) {
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return buffer;
	}

	/**
	 * @param path
	 *            路径
	 * @param displayWidth
	 *            需要显示的宽度
	 * @param displayHeight
	 *            需要显示的高度
	 * @return Bitmap
	 */
	public static Bitmap decodeBitmap(String path, int displayWidth,
			int displayHeight) {
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true;
		// op.inJustDecodeBounds = true;表示我们只读取Bitmap的宽高等信息，不读取像素。
		Bitmap bmp = BitmapFactory.decodeFile(path, op); // 获取尺寸信息
		// op.outWidth表示的是图像真实的宽度
		// op.inSamplySize 表示的是缩小的比例
		// op.inSamplySize = 4,表示缩小1/4的宽和高，1/16的像素，android认为设置为2是最快的。
		// 获取比例大小
		int wRatio = (int) Math.ceil(op.outWidth / (float) displayWidth);
		int hRatio = (int) Math.ceil(op.outHeight / (float) displayHeight);
		// 如果超出指定大小，则缩小相应的比例
		if (wRatio > 1 && hRatio > 1) {
			if (wRatio > hRatio) {
				// 如果太宽，我们就缩小宽度到需要的大小，注意，高度就会变得更加的小。
				op.inSampleSize = wRatio;
			} else {
				op.inSampleSize = hRatio;
			}
		}
		op.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(path, op);
		// 从原Bitmap创建一个给定宽高的Bitmap
		return Bitmap
				.createScaledBitmap(bmp, displayWidth, displayHeight, true);
	}

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}


	public static int getDisplayWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public static int getDisplayHeight(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	public static Bitmap drawable2Bitmap(Drawable d) {
		Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d
				.getIntrinsicHeight(),
				d.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
						: Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		d.draw(canvas);
		return bitmap;
	}

	public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap oldbmp = drawable2Bitmap(drawable);
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
				matrix, true);
		return new BitmapDrawable(null, newbmp);
	}

	public static Drawable zoomDrawable2(Drawable drawable, float scale) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap oldbmp = drawable2Bitmap(drawable);
		Matrix matrix = new Matrix();
		// float scaleWidth = ((float) w / width);
		// float scaleHeight = ((float) h / height);
		matrix.postScale(scale, scale);
		Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
				matrix, true);
		return new BitmapDrawable(null, newbmp);
	}

	public static Bitmap ImageCrop(Drawable drawable, int newW, int newH) {
		int w = drawable.getIntrinsicWidth(); // 得到图片的宽，高
		int h = drawable.getIntrinsicHeight();

		int retX = w > h ? (w - h) / 2 : 0;// 基于原图，取正方形左上角x坐标
		int retY = w > h ? 0 : (h - w) / 2;

		// 下面这句是关键
		Bitmap bm = drawable2Bitmap(drawable);
		return Bitmap.createBitmap(bm, retX, retY, newW, newH, null, false);
	}

	/**
	 * 读取图片属性：旋转的角度
	 *
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/*
	 * 旋转图片
	 *
	 * @param angle
	 *
	 * @param bitmap
	 *
	 * @return Bitmap
	 */
	public static Bitmap rotatingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();

		matrix.postRotate(angle);
		System.out.println("angle2=" + angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static long getdayInterval(String day1, String day2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

		try {
			Date d1 = df.parse(day1);
			Date d2 = df.parse(day2);
			long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别

			long days = diff / (1000 * 60 * 60 * 24);
			// long hours = (diff - days * (1000 * 60 * 60 * 24))
			// / (1000 * 60 * 60);
			// long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
			// * (1000 * 60 * 60))
			// / (1000 * 60);

			return (days);
		} catch (Exception e) {
			return Long.valueOf(10000).intValue();
		}
	}



	public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {

		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight, scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);

		// make a Drawable from Bitmap to allow to set the Bitmap
		// to the ImageView, ImageButton or what ever
		return (resizedBitmap);

	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/*
	 * 判断是否为浮点数，包括double和float
	 *
	 * @param str 传入的字符串
	 *
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		if (str == null || str.equals(""))
			return false;
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/*
	 * 判断是否为整数
	 *
	 * @param str 传入的字符串
	 *
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		if (str == null || str.equals(""))
			return false;
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	public static boolean createDirs(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return file.mkdirs();
		} else if (file.isFile()) {
			file.delete();
			return file.mkdirs();
		} else {
			return true;
		}
	}



	public static boolean isSDcardAvailable() {
		String sdStatus = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(sdStatus);
	}

	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
			if (sdDir != null) {
				return sdDir.toString();
			}
		}
		return "";

	}

	public static String getFormatPrice(String number) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.setMaximumFractionDigits(2);
		df.applyPattern("0.00");
		return df.format(Double.parseDouble(number));
	}

	public static Date getSpecifiedDayAfter(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		return c.getTime();
	}
}
