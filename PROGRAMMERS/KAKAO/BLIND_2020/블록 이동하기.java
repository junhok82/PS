package KAKAO.BLIND2020;

import java.util.LinkedList;
import java.util.Queue;

public class 블록이동하기 {
	private static int[] dy = {0, 1, 0, -1};
	private static int[] dx = {1, 0, -1, 0};
	private static int N;
	private static Queue<Location> q = new LinkedList<>();
	private static int[][] visited;
	private static Location currLocation;
	private static int r1;
	private static int r2;
	private static int c1;
	private static int c2;

	private final static int UP = 1;
	private final static int DOWN = 2;
	private final static int LEFT = 4;
	private final static int RIGHT = 8;

	public static void main(String[] args) {
	}

	public static int solution(int[][] board) {
		N = board.length;
		visited = new int[N + 1][N + 1];
		return bfs(board);
	}

	private static int bfs(int[][] board) {
		Location location = new Location(0, 0, 0, 1);
		q.offer(location);
		visited[0][1] |= DOWN;
		visited[1][1] |= UP;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			System.out.println("size : " + size);
			for (int i = 0; i < size; i++) {
				currLocation = q.poll();
				r1 = currLocation.r1;
				r2 = currLocation.r2;
				c1 = currLocation.c1;
				c2 = currLocation.c2;
				if ((r1 == N - 1 && c1 == N - 1) || (r2 == N - 1 && c2 == N - 1))
					return cnt;

				for (int k = 0; k < 4; k++) {
					int nr1 = r1 + dy[k];
					int nc1 = c1 + dx[k];
					int nr2 = r2 + dy[k];
					int nc2 = c2 + dx[k];
					Location nextLocation = new Location(nr1, nc1, nr2, nc2);

					if (nr1 < 0 || nc1 < 0 || nr2 < 0 || nc2 < 0 || nr1 >= N || nc1 >= N || nr2 >= N || nc2 >= N
						|| board[nr1][nc1] == 1 || board[nr2][nc2] == 1)
						continue;
					if (nr1 == nr2 && vertical(nr1, nc1, nc2))
						q.offer(nextLocation);
					else if (nc1 == nc2 && horizon(nr1, nr2, nc1))
						q.offer(nextLocation);
				}

				if (r1 == r2) {
					goUp(board);
					goDown(board);
				} else if (c1 == c2) {
					goRight(board);
					goLeft(board);
				}
			}
			cnt++;
		}
		return -1;
	}

	private static void goLeft(int[][] board) {
		int nc = c1 - 1;
		int minR = Math.min(r1, r2);
		int maxR = Math.max(r1, r2);

		Location tempLocation = new Location(minR, c1, minR, nc);
		Location nextLocation = new Location(maxR, nc, maxR, c2);
		if (nc < 0 || board[r1][nc] == 1 || board[r2][nc] == 1)
			return;
		if (vertical(maxR, nc + 1, nc))
			q.offer(nextLocation);

		if (vertical(minR, nc + 1, nc))
			q.offer(tempLocation);
	}

	private static void goRight(int[][] board) {
		int nc = c1 + 1;
		int minR = Math.min(r1, r2);
		int maxR = Math.max(r1, r2);

		Location tempLocation = new Location(minR, c1, minR, nc);
		Location nextLocation = new Location(maxR, nc, maxR, c2);
		if (nc >= N || board[r1][nc] == 1 || board[r2][nc] == 1)
			return;
		if (vertical(maxR, nc - 1, nc))
			q.offer(nextLocation);

		if (vertical(minR, nc - 1, nc))
			q.offer(tempLocation);
	}

	private static boolean horizon(int nr1, int nr2, int nc1) {
		int minC = nc1;
		int maxC = minC + 1;
		int maxR = Math.max(nr1, nr2);
		if ((visited[maxR][maxC] & LEFT) != 0 || (visited[maxR][minC] & RIGHT) != 0)
			return false;
		visited[maxR][maxC] |= LEFT;
		visited[maxR][minC] |= RIGHT;
		return true;
	}

	private static boolean vertical(int nr1, int nc1, int nc2) {
		int maxC = Math.max(nc1, nc2);
		int minR = nr1;
		int maxR = minR + 1;
		if ((visited[minR][maxC] & DOWN) != 0 || (visited[maxR][maxC] & UP) != 0)
			return false;
		visited[minR][maxC] |= DOWN;
		visited[maxR][maxC] |= UP;
		return true;
	}

	private static void goDown(int[][] board) {
		int nr = r1 + 1;
		int minC = Math.min(c1, c2);
		int maxC = Math.max(c1, c2);

		Location tempLocation = new Location(r1, minC, nr, minC);
		Location nextLocation = new Location(nr, maxC, r2, maxC);
		if (nr >= N || board[nr][c1] == 1 || board[nr][c2] == 1)
			return;
		if (horizon(nr - 1, nr, maxC))
			q.offer(nextLocation);

		if (horizon(nr - 1, nr, minC))
			q.offer(tempLocation);

	}

	private static void goUp(int[][] board) {
		int nr = r1 - 1;
		int minC = Math.min(c1, c2);
		int maxC = Math.max(c1, c2);

		Location tempLocation = new Location(r1, minC, nr, minC);
		Location nextLocation = new Location(nr, maxC, r2, maxC);
		if (nr < 0 || board[nr][c1] == 1 || board[nr][c2] == 1)
			return;
		if (horizon(nr + 1, nr, maxC))
			q.offer(nextLocation);

		if (horizon(nr + 1, nr, minC))
			q.offer(tempLocation);
	}

	static class Location {
		int r1, r2, c1, c2;

		public Location(int rr1, int cc1, int rr2, int cc2) {
			this.r1 = rr1;
			this.r2 = rr2;
			this.c1 = cc1;
			this.c2 = cc2;
		}
	}
}
