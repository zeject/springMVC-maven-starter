package com.toolkit;

import java.util.HashMap;
import java.util.Map;

import com.config.Constants;

/**
 * 极光推送
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JPush {

	// "android", "ios", "winphone", "all"
	private String[] platform;
	// tag, tag_and, alias, registration_id, all

	// audience
	private String[] tag;
	private String[] tag_and;
	private String[] alias;
	private String[] alia;
	private String[] registration_id;

	// notification
	private String alert;
	private Map extras;
	// notification android
	private String title = "鹊桥惠";
	private int builder_id = 3;

	// notification ios
	private String sound;
	private int badge = 0;
	private boolean contentAvailable = true;

	// message
	private String msg_content;
	private String msg_title;
	private String msg_content_type;
	private Map msg_extras;

	// options
	/*
	 * 纯粹用来作为 API 调用标识，API 返回时被原样返回，以方便 API 调用方匹配请求与返回。
	 */
	private int sendno;
	/*
	 * 推送当前用户不在线时，为该用户保留多长时间的离线消息， 以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0
	 * 表示不保留离线消息，只有推送当前在线的用户可以收到。
	 */
	private int time_to_live = 86400;
	/*
	 * 如果当前的推送要覆盖之前的一条推送，这里填写前一条推送的 msg_id 就会产生覆盖效果，即：1）该 msg_id
	 * 离线收到的消息是覆盖后的内容；2）即使该 msg_id Android
	 * 端用户已经收到，如果通知栏还未清除，则新的消息内容会覆盖之前这条通知；覆盖功能起作用的时限是：1 天。如果在覆盖指定时限内该 msg_id
	 * 不存在，则返回 1003 错误，提示不是一次有效的消息覆盖操作，当前的消息不会被推送。
	 */
	private long override_msg_id;
	/*
	 * True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。JPush 官方 API LIbrary (SDK)
	 * 默认设置为推送 “开发环境”。
	 */
	private boolean apns_production = true;
	/*
	 * 又名缓慢推送，把原本尽可能快的推送速度，降低下来，给定的n分钟内，均匀地向这次推送的目标用户推送。最大值为1400.未设置则不是定速推送。
	 */
	private int big_push_duration;

	public static void main(String[] args) {
		new JPush("欢迎使用鹊桥慧").setAlias(new String[] { "13834653506" }).setBadge(0).setTime_to_live(0).send();
	}

	/**
	 * 通知
	 * 
	 * @param msg
	 */
	public JPush(String alert) {
		this.alert = alert;
	}

	private Map build() {
		Map parmaMap = new HashMap();
		if (this.platform == null) {
			parmaMap.put("platform", "all");
		} else {
			parmaMap.put("platform", this.platform);
		}

		if (this.tag == null && this.tag_and == null && this.alias == null && this.registration_id == null) {
			parmaMap.put("audience", "all");
		} else {
			Map audienceMap = new HashMap();
			if (this.tag != null) {
				audienceMap.put("tag", this.tag);
			}
			if (this.tag_and != null) {
				audienceMap.put("tag_and", this.tag_and);
			}
			if (this.alia != null) {
				audienceMap.put("alias", this.alia);
			}
			if (this.registration_id != null) {
				audienceMap.put("registration_id", this.registration_id);
			}
			parmaMap.put("audience", audienceMap);
		}

		if (this.alert != null) {
			Map notificationMap = new HashMap();

			Map notificationAndroidMap = new HashMap();
			notificationAndroidMap.put("alert", this.alert);
			if (this.title != null) {
				notificationAndroidMap.put("title", this.title);
			}
			if (this.builder_id != 0) {
				notificationAndroidMap.put("builder_id", this.builder_id);
			}
			if (this.extras != null) {
				notificationAndroidMap.put("extras", this.extras);
			}

			Map notificationIOSMap = new HashMap();
			notificationIOSMap.put("alert", this.alert);
			if (this.sound != null) {
				notificationIOSMap.put("sound", this.sound);
			}
			// if (this.badge != 0) {
			notificationIOSMap.put("badge", this.badge);
			// }
			if (this.contentAvailable) {
				notificationIOSMap.put("content-available", this.contentAvailable);
			}
			if (this.extras != null) {
				notificationIOSMap.put("extras", this.extras);
			}
			notificationMap.put("android", notificationAndroidMap);
			notificationMap.put("ios", notificationIOSMap);

			parmaMap.put("notification", notificationMap);
		}

		if (this.msg_content != null) {
			Map messageMap = new HashMap();
			messageMap.put("msg_content", this.msg_content);
			if (msg_title != null) {
				messageMap.put("title", this.msg_title);
			}
			if (msg_content_type != null) {
				messageMap.put("content_type", this.msg_content_type);
			}
			if (msg_extras != null) {
				messageMap.put("extras", this.msg_extras);
			}
			parmaMap.put("message", messageMap);
		}

		Map optionsMap = new HashMap();
		if (this.sendno != 0) {
			optionsMap.put("sendno", this.sendno);
		}
		optionsMap.put("time_to_live", this.time_to_live);
		if (this.override_msg_id != 0) {
			optionsMap.put("override_msg_id", this.override_msg_id);
		}
		if (this.apns_production) {
			optionsMap.put("apns_production", this.apns_production);
		}
		if (this.big_push_duration != 0) {
			optionsMap.put("big_push_duration", this.big_push_duration);
		}
		parmaMap.put("options", optionsMap);

		return parmaMap;
	}

	public void send() {
		Map headMap = new HashMap();
		headMap.put("Authorization", Constants.JPushAuthor);
		for (int i = 0; i < alias.length; i++) {
			setAlia(new String[] { alias[i] });
			Map csMap = new HashMap();
			csMap.put("phone", alias[i]);
			setExtras(csMap);
			HttpServer.doPost("https://api.jpush.cn/v3/push", headMap, build());
		}
	}

	public JPush setPlatform(String[] platform) {
		this.platform = platform;
		return this;
	}

	public JPush setTag(String[] tag) {
		this.tag = tag;
		return this;
	}

	public JPush setTag_and(String[] tag_and) {
		this.tag_and = tag_and;
		return this;
	}

	public JPush setAlias(String[] alias) {
		this.alias = alias;
		return this;
	}

	public JPush setRegistration_id(String[] registration_id) {
		this.registration_id = registration_id;
		return this;
	}

	public JPush setAlert(String alert) {
		this.alert = alert;
		return this;
	}

	public JPush setExtras(Map extras) {
		this.extras = extras;
		return this;
	}

	public JPush setTitle(String title) {
		this.title = title;
		return this;
	}

	public JPush setBuilder_id(int builder_id) {
		this.builder_id = builder_id;
		return this;
	}

	public JPush setSound(String sound) {
		this.sound = sound;
		return this;
	}

	public JPush setBadge(int badge) {
		this.badge = badge;
		return this;
	}

	public JPush setContentAvailable(boolean contentAvailable) {
		this.contentAvailable = contentAvailable;
		return this;
	}

	public JPush setMsg_content(String msg_content) {
		this.msg_content = msg_content;
		return this;
	}

	public JPush setMsg_title(String msg_title) {
		this.msg_title = msg_title;
		return this;
	}

	public JPush setMsg_content_type(String msg_content_type) {
		this.msg_content_type = msg_content_type;
		return this;
	}

	public JPush setMsg_extras(Map msg_extras) {
		this.msg_extras = msg_extras;
		return this;
	}

	public JPush setSendno(int sendno) {
		this.sendno = sendno;
		return this;
	}

	public JPush setTime_to_live(int time_to_live) {
		this.time_to_live = time_to_live;
		return this;
	}

	public JPush setOverride_msg_id(long override_msg_id) {
		this.override_msg_id = override_msg_id;
		return this;
	}

	public JPush setApns_production(boolean apns_production) {
		this.apns_production = apns_production;
		return this;
	}

	public JPush setBig_push_duration(int big_push_duration) {
		this.big_push_duration = big_push_duration;
		return this;
	}

	public void setAlia(String[] alia) {
		this.alia = alia;
	}

}
