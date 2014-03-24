//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%201
//write kazua

import java.util.function.*;
import java.util.stream.*;

public class problem001{
    Function<Integer, Integer> answer = i -> (IntStream.range(1, i)).filter(n -> n % 3 == 0 || n % 5 == 0).sum();
    public static void main(String[] args){
        problem001 p001 = new problem001();
        System.out.println(p001.answer.apply(1000));
    }
}