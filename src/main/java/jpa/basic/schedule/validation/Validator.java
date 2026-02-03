package jpa.basic.schedule.validation;

/**
 * 도메인 클래스에서 생성자를 통해 생성하는 초기 단계로 먼저 입력값 검증하기
 */
public class Validator {

    /**
     * 모든 도메인 클래스의 생성자에서 검증이 필요하기에 static으로 선언하기
     * 상태값이 필요없고 객체 생성 없이 단순 검증용으로 사용
     * 전달받은 컬럼의 길이가 지정한 최대 길이를 넘었는 지, null인지 체크한다.
     *
     * @param input : 전달받은 각 컬럼 데이터
     * @param maxLength : 최대 길이
     * @param msg : 적합하지 않을 경우, 어떤 메시지를 보낼지
     */
    public static void checkLength(String input, int maxLength, String msg) {
        if (input != null && input.length() > maxLength) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * null이 아닌지 체크하는 메서드
     *
     * @param input : 전달받은 각 컬럼 데이터
     * @param msg : 적합하지 않을 경우, 어떤 메시지를 보낼지
     */
    public static void isNotNull(String input, String msg) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(msg);
        }
    }
}
