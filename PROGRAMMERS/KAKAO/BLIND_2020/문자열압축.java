package KAKAO.BLIND2020;

public class 문자열압축 {
	public static void main(String[] args) {

	}

	public static int solution(String s) {
		int answer = s.length() == 1 ? 1 : Integer.MAX_VALUE;

		for (int i = 1; i < s.length(); i++) {
			StringBuilder tempResult = new StringBuilder();
			int k = 0;
			while (k + i + i <= s.length()) {
				String pattern = s.substring(k, k + i);
				int cnt = 1;

				k += i;
				boolean flag = true;
				while (k + i <= s.length()) {
					flag = false;
					String expression = s.substring(k, k + i);
					if (pattern.equals(expression)) {
						cnt++;
						k += i;
						if (k + i > s.length()) {
							tempResult.append(cnt == 1 ? "" : cnt).append(pattern);
						}
					} else {
						tempResult.append(cnt == 1 ? "" : cnt).append(pattern);
						break;
					}
				}
				if (flag)
					tempResult.append(pattern);
			}
			tempResult.append(s.substring(k, s.length()));
			String temp = tempResult.toString();
			answer = Math.min(answer, temp.length());
		}
		return answer;
	}
}
