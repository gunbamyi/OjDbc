package acc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Account {
	private String accountNo; // 계좌번호
	private int balance;      // 현재잔액
	FileWriter fout = null;   // 출력파일
	String[] tmp = null;
	String anum = "", name, pnum, money, ex, path, time, f;
	String regex = "[0-9]{3,6}-[0-9]{2,6}-[0-9]{2,6}-?[0-9]{1,3}";
	String nameregex = "^[가-힣]{1,5}+$"; // ^[가-힣a-zA-Z]{1,5}+$ 한글+영문
	String pnumrepex = "(02|01|[0-9]{3})-([0-9]+)-([0-9]{4})"; // ([0-9]{3,4})-([0-9]{3,4})-([0-9]{4})

	DecimalFormat dc = new DecimalFormat("###,###,###,###");
	Scanner sc = new Scanner(System.in);
	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	
	// 계좌번호에 해당하는 파일열기
	public String getF(String anum) {
		try {
			fout = new FileWriter(getPath() + anum + ".txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	// 현재 시간 얻어오기
	public String getTime() {
		Date time1 = new Date();
		time = format2.format(time1);
		return time;
	}
	
	// 파일 열기위한 경로 지정하고 경로값만 얻어오기
	public String getPath() {
		try {
			path = AccountApp.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return path;
	}

	public Account(String string) {
		accountNo = string;
		System.out.println(accountNo);
	}

	public Account() {
	}

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = Integer.parseInt(balance);
	}
	
	// 종료 실행
	public boolean shutdown(boolean run) {
//		try {
			System.out.println("종료");
			//getF(anum);
			//fout.write(getTime() + ".\t0.\t0.\t" + dc.format(balance));
			//fout.write("\r\n", 0, 2);
			//fout.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return false;
	}
	
	// 예금 실행
	public void deposit() {
		try {
			getF(anum);
			System.out.print("예금액:");

			money = sc.nextLine();
			System.out.println(Integer.parseInt(money));

			if (Integer.parseInt(money) == 0) {
				System.out.println("0원은 예금할 수 없습니다.");
			} else if (Integer.parseInt(money) < 0) {
				System.out.println("금액에 오류가 있습니다.");
			} else {
				balance += Integer.parseInt(money);
				System.out.println("예금되었습니다. 잔액:" + dc.format(balance) + "원");
				fout.write(getTime() + ".\t" + dc.format(Integer.parseInt(money)) + ".\t0.\t" + dc.format(balance));
				fout.write("\r\n", 0, 2);
				fout.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 출금 실행
	public void witdraw() {
		try {
			getF(anum);
			System.out.print("출금액:");

			money = sc.nextLine();
			if (Integer.parseInt(money) == 0) {
				System.out.println("0원은 출금할 수 없습니다.");
			} else if (balance >= Integer.parseInt(money) && Integer.parseInt(money) > 0) {
				balance -= Integer.parseInt(money);
				System.out.println("출금되었습니다. 잔액:" + dc.format(balance) + "원");
				fout.write(getTime() + ".\t0.\t" + dc.format(Integer.parseInt(money)) + ".\t" + dc.format(balance));
				fout.write("\r\n", 0, 2);
			} else if (Integer.parseInt(money) > balance) {
				System.out.println("잔액이 부족합니다. 잔액:" + dc.format(balance) + "원");
			} else {
				System.out.println("금액에 오류가있습니다.");
			}

			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 잔액 조회
	public void printBalance() {
		try {

			getF(anum);
			System.out.println("잔액조회 :" + dc.format(balance) + "원");
			//fout.write(getTime() + ".\t0.\t0.\t" + dc.format(balance));
			//fout.write("\r\n", 0, 2);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 조회기간 입력 및 날짜포맷 체크 후 해당 기간의 내역 출력 (새로운 파일접근 사용)  
	public void date_check() {
		Date day1 = null;
		Date day2 = null;

		try {
			System.out.println("※yyyy-mm-dd 방식으로 입력하십시오.");
			System.out.print("시작기간을 입력하세요 : ");
			Date year1 = format2.parse(sc.nextLine());
			System.out.print("종료기간을 입력하세요 : ");
			Date year2 = format2.parse(sc.nextLine());
			System.out.println();
			System.out.println("----------------조회 내역-----------------");
			FileReader filereader = new FileReader(getPath() + anum + ".txt");
			BufferedReader bufReader = new BufferedReader(filereader);
			String temp = "";
			day1 = year1;
			day2 = year2;

			while ((temp = bufReader.readLine()) != null) {
				String t = temp.replace("\t", "").replace(" ", "");
				tmp = t.split("[.]");
				try {
					int compare = day1.compareTo(format2.parse(tmp[0]));
					int compare2 = day2.compareTo(format2.parse(tmp[0]));

					if (compare <= 0 && compare2 >= 0) {
						System.out.print("출력:");
						for (int i = 0; i < tmp.length; i++) {
							System.out.print(tmp[i] + "\t");
						}
						System.out.println();
					} else {
//					System.out.println("아냐");
					}
				} catch (ParseException e) {
					continue;
				}
			}
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("올바르지 않은 날짜 형식입니다.ex)2020-01-01과 같이 입력해주십시오.");
		}

	}

	public void accountinfo() {
		try {
			String path = AccountApp.class.getResource("").getPath();
			path = java.net.URLDecoder.decode(path, "UTF-8");
			while (true) {
				System.out.print("계좌번호 입력: ");
				anum = sc.nextLine();
				File file = new File(path + anum + ".txt");
				boolean isExists = file.exists();
				
				//파일이 존재한다면 계좌가 이미 개설된 상태
				if (isExists) {
					fout = new FileWriter(file, true);
					System.out.println("보유계좌입니다. 불러오는중...");
					FileReader filereader = new FileReader(path + anum + ".txt");
					BufferedReader bufReader = new BufferedReader(filereader);
					String temp = "";
					while ((temp = bufReader.readLine()) != null) {
						String t = temp.replace("\t", "").replace(",", "");
						tmp = t.split("[.]");
					}
					setBalance(tmp[3]);
					bufReader.close();
//					fout.write("--------------------");
//					fout.write("\r\n", 0, 2);
					break;
				// 파일이 없으므로 신규 계좌 개설 해야함
				} else {
					if (anum.matches(regex)) {
						System.out.println("신규계좌입니다. 계좌생성중...");
						while (true) {
							System.out.print("이름 입력: ");
							name = sc.nextLine();
							if (name.matches(nameregex)) {
								new Account(anum + " | " + name);
								break;
							} else {
								System.out.println("이름 형식이 올바르지 않습니다.");
								continue;
							}

						}
						while (true) {
							System.out.print("연락처 입력: ");
							pnum = sc.nextLine();
							if (pnum.matches(pnumrepex)) {
								new Account(anum + " | " + name + " | " + pnum);
								break;
							} else {
								System.out.println("연락처 형식이 올바르지 않습니다. ex)010-0000-0000과 같이 입력해주세요.");
								continue;
							}

						}
						fout = new FileWriter(file, true);
						fout.write("계좌번호 : " + anum + " 성명 : " + name + " 연락처 : " + pnum);
						fout.write("\r\n", 0, 2);
						fout.write("날짜\t" + "예금\t" + "출금\t" + "잔액");
						fout.write("\r\n", 0, 2);
						break;
					} else {
						System.out.println("계좌번호 형식이 올바르지않습니다. ex)110-000-0000과 같이 입력해주세요.");
						continue;
					}
				}
			}
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("파일이 잘못되었습니다. 관리자에게 문의하세요.");
			System.exit(0);
		}
	}

}
