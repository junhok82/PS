package KAKAO.BLIND2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 오픈채팅방 {
	private static String[] order;
	private static String[] uid;
	private static String[] name;
	private static Map<String, String> mapName;
	private static int size;
	private static String[] answer;

	public static void main(String[] args) {
		String[] input = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
			"Change uid4567 Ryan"};
		System.out.println(Arrays.toString(solution(input)));
	}

	public static String[] solution(String[] record) {
		size = record.length;
		order = new String[size];
		uid = new String[size];
		name = new String[size];
		mapName = new HashMap<>();
		int sz = 0;

		for (int i = 0; i < size; i++) {
			String[] temp = record[i].split(" ");

			order[i] = temp[0];
			uid[i] = temp[1];
			if (!order[i].equals("Leave")) {
				name[i] = temp[2];
				mapName.put(uid[i], name[i]);
			}
			if (!order[i].equals("Change"))
				sz++;
		}
		answer = new String[sz];
		solve();
		return answer;
	}

	private static void solve() {
		int idx = 0;
		for (int i = 0; i < size; i++) {
			String ORDER = order[i];
			String UID = uid[i];
			String NAME = mapName.get(UID);

			if (ORDER.equals("Enter")) {
				answer[idx++] = NAME + "님이 들어왔습니다.";
			} else if (ORDER.equals("Leave")) {
				answer[idx++] = NAME + "님이 나갔습니다.";
			}
		}
	}
}
