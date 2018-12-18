import javax.management.openmbean.InvalidOpenTypeException;
import java.io.IOException;

/**
 * Created by chenzhitao on 2017/11/20
 */
public abstract class InputStreamDemo {
    private static final int MAX_SKIP_BUFFER_SIZE = 2048;

    public abstract int read() throws IOException;

    public int read(byte[] b) throws IOException { 
        return  read(b,0,b.length);
    }

    public int read(byte[] b,int off,int length) throws IOException {
        if (b == null) {
            throw new  IOException();
        }else if (off < 0 || length < 0 || b.length < off + length) {
            throw new IndexOutOfBoundsException();
        }else if (length == 0){
            return 0;
        }
        
        int c = read();
        if (c == -1) {
            return -1;
        }

        b[off] = (byte) c;
        int i = 1;
        try {
            for (; i < length ; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[++off] = (byte) c;
            }
        }catch (IOException ee){

        }
        return i;
    }

    public int skip(long n) throws IOException {
        long remaining = n;
        int nr;

        if (n <= 0){
            return 0;
        }

        int size = (int) Math.min(MAX_SKIP_BUFFER_SIZE,remaining);
        byte [] buf = new byte[size];
        while (remaining > 0 ){
            nr = read(buf,0, (int) Math.min(size,remaining));
            if (nr == -1) {
                break;
            }
            remaining -= nr;
        }
        return (int) (n-remaining);
    }


}
