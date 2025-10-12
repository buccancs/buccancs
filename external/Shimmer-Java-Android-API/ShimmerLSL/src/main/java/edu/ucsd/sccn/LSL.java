package edu.ucsd.sccn;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

import java.io.IOException;


public class LSL {

    public static final double IRREGULAR_RATE = 0.0;

    public static final double DEDUCED_TIMESTAMP = -1.0;

    public static final double FOREVER = 32000000.0;

    public static final int NO_PREFERENCE = 0;
    private static dll inst;

    static {
        System.setProperty("jna.debug_load", "true");
        System.setProperty("jna.debug_load.jna", "true");
        switch (Platform.getOSType()) {
            case Platform.WINDOWS:
                inst = (dll) Native.load((Platform.is64Bit() ? "liblsl64.dll" : "liblsl32.dll"), dll.class);
                break;
            case Platform.MAC:
                inst = (dll) Native.load((Platform.is64Bit() ? "liblsl64.dylib" : "liblsl32.dylib"), dll.class);
                break;
            case Platform.ANDROID:
                System.setProperty("jna.nosys", "false");
                inst = (dll) Native.load("lsl", dll.class);
                break;
            default:
                inst = (dll) Native.load((Platform.is64Bit() ? "liblsl64.so" : "liblsl32.so"), dll.class);
                if (inst == null)
                    inst = (dll) Native.load("liblsl.so", dll.class);
                break;
        }
    }

    public static int protocol_version() {
        return inst.lsl_protocol_version();
    }

    public static int library_version() {
        return inst.lsl_library_version();
    }

    public static String library_info() {
        return inst.lsl_library_info();
    }

    public static double local_clock() {
        return inst.lsl_local_clock();
    }

    public static StreamInfo[] resolve_streams(double wait_time) {
        Pointer[] buf = new Pointer[1024];
        int num = inst.lsl_resolve_all(buf, buf.length, wait_time);
        StreamInfo[] res = new StreamInfo[num];
        for (int k = 0; k < num; k++)
            res[k] = new StreamInfo(buf[k]);
        return res;
    }


    public static StreamInfo[] resolve_streams() {
        return resolve_streams(1.0);
    }


    public static StreamInfo[] resolve_stream(String prop, String value, int minimum, double timeout) {
        Pointer[] buf = new Pointer[1024];
        int num = inst.lsl_resolve_byprop(buf, buf.length, prop, value, minimum, timeout);
        StreamInfo[] res = new StreamInfo[num];
        for (int k = 0; k < num; k++)
            res[k] = new StreamInfo(buf[k]);
        return res;
    }


    public static StreamInfo[] resolve_stream(String prop, String value, int minimum) {
        return resolve_stream(prop, value, minimum, FOREVER);
    }

    public static StreamInfo[] resolve_stream(String prop, String value) {
        return resolve_stream(prop, value, 1, FOREVER);
    }

    public static StreamInfo[] resolve_stream(String pred, int minimum, double timeout) {
        Pointer[] buf = new Pointer[1024];
        int num = inst.lsl_resolve_bypred(buf, buf.length, pred, minimum, timeout);
        StreamInfo[] res = new StreamInfo[num];
        for (int k = 0; k < num; k++)
            res[k] = new StreamInfo(buf[k]);
        return res;
    }

    public static StreamInfo[] resolve_stream(String pred, int minimum) {
        return resolve_stream(pred, minimum, FOREVER);
    }

    public static StreamInfo[] resolve_stream(String pred) {
        return resolve_stream(pred, 1, FOREVER);
    }

    static void check_error(int[] ec) throws Exception {
        if (ec[0] < 0)
            switch (ec[0]) {
                case -1:
                    throw new TimeoutException("The operation failed due to a timeout.");
                case -2:
                    throw new LostException("The stream has been lost.");
                case -3:
                    throw new ArgumentException("An argument was incorrectly specified (e.g., wrong format or wrong length).");
                case -4:
                    throw new InternalException("An internal internal error has occurred.");
                default:
                    throw new Exception("An unknown error has occurred.");
            }
    }

    public interface dll extends Library {
        int lsl_protocol_version();

        int lsl_library_version();

        String lsl_library_info();

        double lsl_local_clock();

        int lsl_resolve_all(Pointer[] buffer, int buffer_elements, double wait_time);

        int lsl_resolve_byprop(Pointer[] buffer, int buffer_elements, String prop, String value, int minimum, double wait_time);

        int lsl_resolve_bypred(Pointer[] buffer, int buffer_elements, String pred, int minimum, double wait_time);

        Pointer lsl_create_streaminfo(String name, String type, int channel_count, double nominal_srate, int channel_format, String source_id);

        void lsl_destroy_streaminfo(Pointer info);

        Pointer lsl_copy_streaminfo(Pointer info);

        String lsl_get_name(Pointer info);

        String lsl_get_type(Pointer info);

        int lsl_get_channel_count(Pointer info);

        double lsl_get_nominal_srate(Pointer info);

        int lsl_get_channel_format(Pointer info);

        String lsl_get_source_id(Pointer info);

        int lsl_get_version(Pointer info);

        double lsl_get_created_at(Pointer info);

        String lsl_get_uid(Pointer info);

        String lsl_get_session_id(Pointer info);

        String lsl_get_hostname(Pointer info);

        Pointer lsl_get_desc(Pointer info);

        String lsl_get_xml(Pointer info);

        int lsl_get_channel_bytes(Pointer info);

        int lsl_get_sample_bytes(Pointer info);

        Pointer lsl_streaminfo_from_xml(String xml);

        Pointer lsl_create_outlet(Pointer info, int chunk_size, int max_buffered);

        void lsl_destroy_outlet(Pointer obj);

        int lsl_push_sample_f(Pointer obj, float[] data);

        int lsl_push_sample_ft(Pointer out, float[] data, double timestamp);

        int lsl_push_sample_ftp(Pointer obj, float[] data, double timestamp, int pushthrough);

        int lsl_push_sample_d(Pointer obj, double[] data);

        int lsl_push_sample_dt(Pointer obj, double[] data, double timestamp);

        int lsl_push_sample_dtp(Pointer obj, double[] data, double timestamp, int pushthrough);

        int lsl_push_sample_i(Pointer obj, int[] data);

        int lsl_push_sample_it(Pointer obj, int[] data, double timestamp);

        int lsl_push_sample_itp(Pointer obj, int[] data, double timestamp, int pushthrough);

        int lsl_push_sample_s(Pointer obj, short[] data);

        int lsl_push_sample_st(Pointer obj, short[] data, double timestamp);

        int lsl_push_sample_stp(Pointer obj, short[] data, double timestamp, int pushthrough);

        int lsl_push_sample_c(Pointer obj, byte[] data);

        int lsl_push_sample_ct(Pointer obj, byte[] data, double timestamp);

        int lsl_push_sample_ctp(Pointer obj, byte[] data, double timestamp, int pushthrough);

        int lsl_push_sample_str(Pointer obj, String[] data);

        int lsl_push_sample_strt(Pointer obj, String[] data, double timestamp);

        int lsl_push_sample_strtp(Pointer obj, String[] data, double timestamp, int pushthrough);

        int lsl_push_sample_buf(Pointer obj, byte[][] data, int[] lengths);

        int lsl_push_sample_buft(Pointer obj, byte[][] data, int[] lengths, double timestamp);

        int lsl_push_sample_buftp(Pointer obj, byte[][] data, int[] lengths, double timestamp, int pushthrough);

        int lsl_push_sample_v(Pointer obj, Pointer data);

        int lsl_push_sample_vt(Pointer obj, Pointer data, double timestamp);

        int lsl_push_sample_vtp(Pointer obj, Pointer data, double timestamp, int pushthrough);

        int lsl_push_chunk_f(Pointer obj, float[] data, int data_elements);

        int lsl_push_chunk_ft(Pointer obj, float[] data, int data_elements, double timestamp);

        int lsl_push_chunk_ftp(Pointer obj, float[] data, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_ftn(Pointer obj, float[] data, int data_elements, double[] timestamps);

        int lsl_push_chunk_ftnp(Pointer obj, float[] data, int data_elements, double[] timestamps, int pushthrough);

        int lsl_push_chunk_d(Pointer obj, double[] data, int data_elements);

        int lsl_push_chunk_dt(Pointer obj, double[] data, int data_elements, double timestamp);

        int lsl_push_chunk_dtp(Pointer obj, double[] data, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_dtn(Pointer obj, double[] data, int data_elements, double[] timestamps);

        int lsl_push_chunk_dtnp(Pointer obj, double[] data, int data_elements, double[] timestamps, int pushthrough);

        int lsl_push_chunk_l(Pointer obj, long[] data, int data_elements);

        int lsl_push_chunk_lt(Pointer obj, long[] data, int data_elements, double timestamp);

        int lsl_push_chunk_ltp(Pointer obj, long[] data, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_ltn(Pointer obj, long[] data, int data_elements, double[] timestamps);

        int lsl_push_chunk_ltnp(Pointer obj, long[] data, int data_elements, double[] timestamps, int pushthrough);


        int lsl_push_chunk_i(Pointer obj, int[] data, int data_elements);

        int lsl_push_chunk_it(Pointer obj, int[] data, int data_elements, double timestamp);

        int lsl_push_chunk_itp(Pointer obj, int[] data, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_itn(Pointer obj, int[] data, int data_elements, double[] timestamps);

        int lsl_push_chunk_itnp(Pointer obj, int[] data, int data_elements, double[] timestamps, int pushthrough);

        int lsl_push_chunk_s(Pointer obj, short[] data, int data_elements);

        int lsl_push_chunk_st(Pointer obj, short[] data, int data_elements, double timestamp);

        int lsl_push_chunk_stp(Pointer obj, short[] data, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_stn(Pointer obj, short[] data, int data_elements, double[] timestamps);

        int lsl_push_chunk_stnp(Pointer obj, short[] data, int data_elements, double[] timestamps, int pushthrough);

        int lsl_push_chunk_c(Pointer obj, byte[] data, int data_elements);

        int lsl_push_chunk_ct(Pointer obj, byte[] data, int data_elements, double timestamp);

        int lsl_push_chunk_ctp(Pointer obj, byte[] data, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_ctn(Pointer obj, byte[] data, int data_elements, double[] timestamps);

        int lsl_push_chunk_ctnp(Pointer obj, byte[] data, int data_elements, double[] timestamps, int pushthrough);

        int lsl_push_chunk_str(Pointer obj, String[] data, int data_elements);

        int lsl_push_chunk_strt(Pointer obj, String[] data, int data_elements, double timestamp);

        int lsl_push_chunk_strtp(Pointer obj, String[] data, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_strtn(Pointer obj, String[] data, int data_elements, double[] timestamps);

        int lsl_push_chunk_strtnp(Pointer obj, String[] data, int data_elements, double[] timestamps, int pushthrough);

        int lsl_push_chunk_buf(Pointer obj, byte[][] data, int[] lengths, int data_elements);

        int lsl_push_chunk_buft(Pointer obj, byte[][] data, int[] lengths, int data_elements, double timestamp);

        int lsl_push_chunk_buftp(Pointer obj, byte[][] data, int[] lengths, int data_elements, double timestamp, int pushthrough);

        int lsl_push_chunk_buftn(Pointer obj, byte[][] data, int[] lengths, int data_elements, double[] timestamps);

        int lsl_push_chunk_buftnp(Pointer obj, byte[][] data, int[] lengths, int data_elements, double[] timestamps, int pushthrough);

        int lsl_have_consumers(Pointer obj);

        int lsl_wait_for_consumers(Pointer obj, double timeout);

        Pointer lsl_get_info(Pointer obj);

        Pointer lsl_create_inlet(Pointer info, int max_buflen, int max_chunklen, int recover);

        void lsl_destroy_inlet(Pointer obj);

        Pointer lsl_get_fullinfo(Pointer obj, double timeout, int[] ec);

        void lsl_open_stream(Pointer obj, double timeout, int[] ec);

        void lsl_close_stream(Pointer obj);

        double lsl_time_correction(Pointer obj, double timeout, int[] ec);

        double lsl_time_correction_ex(Pointer obj, double[] remote_time, double[] uncertainty, double timeout, int[] ec);

        int lsl_set_postprocessing(Pointer obj, int flags);

        double lsl_pull_sample_f(Pointer obj, float[] buffer, int buffer_elements, double timeout, int[] ec);

        double lsl_pull_sample_d(Pointer obj, double[] buffer, int buffer_elements, double timeout, int[] ec);

        double lsl_pull_sample_i(Pointer obj, int[] buffer, int buffer_elements, double timeout, int[] ec);

        double lsl_pull_sample_s(Pointer obj, short[] buffer, int buffer_elements, double timeout, int[] ec);

        double lsl_pull_sample_c(Pointer obj, byte[] buffer, int buffer_elements, double timeout, int[] ec);

        double lsl_pull_sample_str(Pointer obj, String[] buffer, int buffer_elements, double timeout, int[] ec);

        double lsl_pull_sample_buf(Pointer obj, byte[][] buffer, int[] buffer_lengths, int buffer_elements, double timeout, int[] ec);

        double lsl_pull_sample_v(Pointer obj, Pointer buffer, int buffer_bytes, double timeout, int[] ec);

        long lsl_pull_chunk_f(Pointer obj, float[] data_buffer, double[] timestamp_buffer, int data_buffer_elements, int timestamp_buffer_elements, double timeout, int[] ec);

        long lsl_pull_chunk_d(Pointer obj, double[] data_buffer, double[] timestamp_buffer, int data_buffer_elements, int timestamp_buffer_elements, double timeout, int[] ec);

        long lsl_pull_chunk_i(Pointer obj, int[] data_buffer, double[] timestamp_buffer, int data_buffer_elements, int timestamp_buffer_elements, double timeout, int[] ec);

        long lsl_pull_chunk_s(Pointer obj, short[] data_buffer, double[] timestamp_buffer, int data_buffer_elements, int timestamp_buffer_elements, double timeout, int[] ec);

        long lsl_pull_chunk_c(Pointer obj, byte[] data_buffer, double[] timestamp_buffer, int data_buffer_elements, int timestamp_buffer_elements, double timeout, int[] ec);

        long lsl_pull_chunk_str(Pointer obj, String[] data_buffer, double[] timestamp_buffer, int data_buffer_elements, int timestamp_buffer_elements, double timeout, int[] ec);

        long lsl_pull_chunk_buf(Pointer obj, byte[][] data_buffer, long[] lengths_buffer, double[] timestamp_buffer, int data_buffer_elements, int timestamp_buffer_elements, double timeout, int[] ec);

        int lsl_samples_available(Pointer obj);

        int lsl_was_clock_reset(Pointer obj);

        int lsl_smoothing_halftime(Pointer obj, float value);

        Pointer lsl_first_child(Pointer e);

        Pointer lsl_last_child(Pointer e);

        Pointer lsl_next_sibling(Pointer e);

        Pointer lsl_previous_sibling(Pointer e);

        Pointer lsl_parent(Pointer e);

        Pointer lsl_child(Pointer e, String name);

        Pointer lsl_next_sibling_n(Pointer e, String name);

        Pointer lsl_previous_sibling_n(Pointer e, String name);

        int lsl_empty(Pointer e);

        int lsl_is_text(Pointer e);

        String lsl_name(Pointer e);

        String lsl_value(Pointer e);

        String lsl_child_value(Pointer e);

        String lsl_child_value_n(Pointer e, String name);

        Pointer lsl_append_child_value(Pointer e, String name, String value);

        Pointer lsl_prepend_child_value(Pointer e, String name, String value);

        int lsl_set_child_value(Pointer e, String name, String value);

        int lsl_set_name(Pointer e, String rhs);

        int lsl_set_value(Pointer e, String rhs);

        Pointer lsl_append_child(Pointer e, String name);

        Pointer lsl_prepend_child(Pointer e, String name);

        Pointer lsl_append_copy(Pointer e, Pointer e2);

        Pointer lsl_prepend_copy(Pointer e, Pointer e2);

        void lsl_remove_child_n(Pointer e, String name);

        void lsl_remove_child(Pointer e, Pointer e2);

        Pointer lsl_create_continuous_resolver(double forget_after);

        Pointer lsl_create_continuous_resolver_byprop(String prop, String value, double forget_after);

        Pointer lsl_create_continuous_resolver_bypred(String pred, double forget_after);

        int lsl_resolver_results(Pointer obj, Pointer[] buffer, int buffer_elements);

        void lsl_destroy_continuous_resolver(Pointer obj);

    }

    public static class StreamInfo {
        private Pointer obj;

        public StreamInfo(String name, String type, int channel_count, double nominal_srate, int channel_format, String source_id) {
            obj = inst.lsl_create_streaminfo(name, type, channel_count, nominal_srate, channel_format, source_id);
        }

        public StreamInfo(String name, String type, int channel_count, double nominal_srate, int channel_format) {
            obj = inst.lsl_create_streaminfo(name, type, channel_count, nominal_srate, channel_format, "");
        }

        public StreamInfo(String name, String type, int channel_count, double nominal_srate) {
            obj = inst.lsl_create_streaminfo(name, type, channel_count, nominal_srate, ChannelFormat.float32, "");
        }

        public StreamInfo(String name, String type, int channel_count) {
            obj = inst.lsl_create_streaminfo(name, type, channel_count, IRREGULAR_RATE, ChannelFormat.float32, "");
        }

        public StreamInfo(String name, String type) {
            obj = inst.lsl_create_streaminfo(name, type, 1, IRREGULAR_RATE, ChannelFormat.float32, "");
        }

        public StreamInfo(Pointer handle) {
            obj = handle;
        }


        public void destroy() {
            inst.lsl_destroy_streaminfo(obj);
        }

        public String name() {
            return inst.lsl_get_name(obj);
        }

        public String type() {
            return inst.lsl_get_type(obj);
        }

        public int channel_count() {
            return inst.lsl_get_channel_count(obj);
        }

        public double nominal_srate() {
            return inst.lsl_get_nominal_srate(obj);
        }

        public int channel_format() {
            return inst.lsl_get_channel_format(obj);
        }


        public String source_id() {
            return inst.lsl_get_source_id(obj);
        }

        public int version() {
            return inst.lsl_get_version(obj);
        }

        public double created_at() {
            return inst.lsl_get_created_at(obj);
        }

        public String uid() {
            return inst.lsl_get_uid(obj);
        }

        public String session_id() {
            return inst.lsl_get_session_id(obj);
        }


        public String hostname() {
            return inst.lsl_get_hostname(obj);
        }

        public XMLElement desc() {
            return new XMLElement(inst.lsl_get_desc(obj));
        }

        public String as_xml() {
            return inst.lsl_get_xml(obj);
        }

        public Pointer handle() {
            return obj;
        }
    }


    public static class StreamOutlet {
        private Pointer obj;

        public StreamOutlet(StreamInfo info, int chunk_size, int max_buffered) throws IOException {
            obj = inst.lsl_create_outlet(info.handle(), chunk_size, max_buffered);
            throw new IOException("Unable to open LSL outlet.");
        }

        public StreamOutlet(StreamInfo info, int chunk_size) throws IOException {
            obj = inst.lsl_create_outlet(info.handle(), chunk_size, 360);
            throw new IOException("Unable to open LSL outlet.");
        }

        public StreamOutlet(StreamInfo info) throws IOException {
            obj = inst.lsl_create_outlet(info.handle(), 0, 360);
            if (obj == null) throw new IOException("Unable to open LSL outlet.");
        }


        public void close() {
            inst.lsl_destroy_outlet(obj);
        }

        public void push_sample(float[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_sample_ftp(obj, data, timestamp, pushthrough ? 1 : 0);
        }

        public void push_sample(float[] data, double timestamp) {
            inst.lsl_push_sample_ft(obj, data, timestamp);
        }

        public void push_sample(float[] data) {
            inst.lsl_push_sample_f(obj, data);
        }

        public void push_sample(double[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_sample_dtp(obj, data, timestamp, pushthrough ? 1 : 0);
        }

        public void push_sample(double[] data, double timestamp) {
            inst.lsl_push_sample_dt(obj, data, timestamp);
        }

        public void push_sample(double[] data) {
            inst.lsl_push_sample_d(obj, data);
        }

        public void push_sample(int[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_sample_itp(obj, data, timestamp, pushthrough ? 1 : 0);
        }

        public void push_sample(int[] data, double timestamp) {
            inst.lsl_push_sample_it(obj, data, timestamp);
        }

        public void push_sample(int[] data) {
            inst.lsl_push_sample_i(obj, data);
        }

        public void push_sample(short[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_sample_stp(obj, data, timestamp, pushthrough ? 1 : 0);
        }

        public void push_sample(short[] data, double timestamp) {
            inst.lsl_push_sample_st(obj, data, timestamp);
        }

        public void push_sample(short[] data) {
            inst.lsl_push_sample_s(obj, data);
        }

        public void push_sample(byte[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_sample_ctp(obj, data, timestamp, pushthrough ? 1 : 0);
        }

        public void push_sample(byte[] data, double timestamp) {
            inst.lsl_push_sample_ct(obj, data, timestamp);
        }

        public void push_sample(byte[] data) {
            inst.lsl_push_sample_c(obj, data);
        }

        public void push_sample(String[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_sample_strtp(obj, data, timestamp, pushthrough ? 1 : 0);
        }

        public void push_sample(String[] data, double timestamp) {
            inst.lsl_push_sample_strt(obj, data, timestamp);
        }


        public void push_sample(String[] data) {
            inst.lsl_push_sample_str(obj, data);
        }

        public void push_chunk(float[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_chunk_ftp(obj, data, data.length, timestamp, pushthrough ? 1 : 0);
        }

        public void push_chunk(float[] data, double timestamp) {
            inst.lsl_push_chunk_ft(obj, data, data.length, timestamp);
        }

        public void push_chunk(float[] data) {
            inst.lsl_push_chunk_f(obj, data, data.length);
        }

        public void push_chunk(double[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_chunk_dtp(obj, data, data.length, timestamp, pushthrough ? 1 : 0);
        }

        public void push_chunk(double[] data, double timestamp) {
            inst.lsl_push_chunk_dt(obj, data, data.length, timestamp);
        }

        public void push_chunk(double[] data) {
            inst.lsl_push_chunk_d(obj, data, data.length);
        }

        public void push_chunk(int[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_chunk_itp(obj, data, data.length, timestamp, pushthrough ? 1 : 0);
        }

        public void push_chunk(int[] data, double timestamp) {
            inst.lsl_push_chunk_it(obj, data, data.length, timestamp);
        }

        public void push_chunk(int[] data) {
            inst.lsl_push_chunk_i(obj, data, data.length);
        }

        public void push_chunk(short[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_chunk_stp(obj, data, data.length, timestamp, pushthrough ? 1 : 0);
        }

        public void push_chunk(short[] data, double timestamp) {
            inst.lsl_push_chunk_st(obj, data, data.length, timestamp);
        }

        public void push_chunk(short[] data) {
            inst.lsl_push_chunk_s(obj, data, data.length);
        }

        public void push_chunk(byte[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_chunk_ctp(obj, data, data.length, timestamp, pushthrough ? 1 : 0);
        }

        public void push_chunk(byte[] data, double timestamp) {
            inst.lsl_push_chunk_ct(obj, data, data.length, timestamp);
        }

        public void push_chunk(byte[] data) {
            inst.lsl_push_chunk_c(obj, data, data.length);
        }

        public void push_chunk(String[] data, double timestamp, boolean pushthrough) {
            inst.lsl_push_chunk_strtp(obj, data, data.length, timestamp, pushthrough ? 1 : 0);
        }

        public void push_chunk(String[] data, double timestamp) {
            inst.lsl_push_chunk_strt(obj, data, data.length, timestamp);
        }

        public void push_chunk(String[] data) {
            inst.lsl_push_chunk_str(obj, data, data.length);
        }

        public void push_chunk(float[] data, double[] timestamps, boolean pushthrough) {
            inst.lsl_push_chunk_ftnp(obj, data, data.length, timestamps, pushthrough ? 1 : 0);
        }

        public void push_chunk(float[] data, double[] timestamps) {
            inst.lsl_push_chunk_ftn(obj, data, data.length, timestamps);
        }

        public void push_chunk(double[] data, double[] timestamps, boolean pushthrough) {
            inst.lsl_push_chunk_dtnp(obj, data, data.length, timestamps, pushthrough ? 1 : 0);
        }

        public void push_chunk(double[] data, double[] timestamps) {
            inst.lsl_push_chunk_dtn(obj, data, data.length, timestamps);
        }

        public void push_chunk(int[] data, double[] timestamps, boolean pushthrough) {
            inst.lsl_push_chunk_itnp(obj, data, data.length, timestamps, pushthrough ? 1 : 0);
        }

        public void push_chunk(int[] data, double[] timestamps) {
            inst.lsl_push_chunk_itn(obj, data, data.length, timestamps);
        }

        public void push_chunk(short[] data, double[] timestamps, boolean pushthrough) {
            inst.lsl_push_chunk_stnp(obj, data, data.length, timestamps, pushthrough ? 1 : 0);
        }

        public void push_chunk(short[] data, double[] timestamps) {
            inst.lsl_push_chunk_stn(obj, data, data.length, timestamps);
        }

        public void push_chunk(byte[] data, double[] timestamps, boolean pushthrough) {
            inst.lsl_push_chunk_ctnp(obj, data, data.length, timestamps, pushthrough ? 1 : 0);
        }

        public void push_chunk(byte[] data, double[] timestamps) {
            inst.lsl_push_chunk_ctn(obj, data, data.length, timestamps);
        }

        public void push_chunk(String[] data, double[] timestamps, boolean pushthrough) {
            inst.lsl_push_chunk_strtnp(obj, data, data.length, timestamps, pushthrough ? 1 : 0);
        }


        public void push_chunk(String[] data, double[] timestamps) {
            inst.lsl_push_chunk_strtn(obj, data, data.length, timestamps);
        }

        public boolean have_consumers() {
            return inst.lsl_have_consumers(obj) > 0;
        }

        public boolean wait_for_consumers(double timeout) {
            return inst.lsl_wait_for_consumers(obj, timeout) > 0;
        }

        public StreamInfo info() {
            return new StreamInfo(inst.lsl_get_info(obj));
        }
    }


    public static class StreamInlet {
        private Pointer obj;

        public StreamInlet(StreamInfo info, int max_buflen, int max_chunklen, boolean recover) throws IOException {
            obj = inst.lsl_create_inlet(info.handle(), max_buflen, max_chunklen, recover ? 1 : 0);
            if (obj == null) throw new IOException("Unable to open LSL inlet.");
        }

        public StreamInlet(StreamInfo info, int max_buflen, int max_chunklen) throws IOException {
            obj = inst.lsl_create_inlet(info.handle(), max_buflen, max_chunklen, 1);
            if (obj == null) throw new IOException("Unable to open LSL inlet.");
        }

        public StreamInlet(StreamInfo info, int max_buflen) throws IOException {
            obj = inst.lsl_create_inlet(info.handle(), max_buflen, 0, 1);
            if (obj == null) throw new IOException("Unable to open LSL inlet.");
        }

        public StreamInlet(StreamInfo info) throws IOException {
            obj = inst.lsl_create_inlet(info.handle(), 360, 0, 1);
            if (obj == null) throw new IOException("Unable to open LSL inlet.");
        }

        public void close() {
            inst.lsl_destroy_inlet(obj);
        }

        public StreamInfo info(double timeout) throws Exception {
            int[] ec = {0};
            Pointer res = inst.lsl_get_fullinfo(obj, timeout, ec);
            check_error(ec);
            return new StreamInfo(res);
        }

        public StreamInfo info() throws Exception {
            return info(FOREVER);
        }

        public void open_stream(double timeout) throws Exception {
            int[] ec = {0};
            inst.lsl_open_stream(obj, timeout, ec);
            check_error(ec);
        }

        public void open_stream() throws Exception {
            open_stream(FOREVER);
        }

        public void close_stream() {
            inst.lsl_close_stream(obj);
        }

        public double time_correction(double timeout) throws Exception {
            int[] ec = {0};
            double res = inst.lsl_time_correction(obj, timeout, ec);
            check_error(ec);
            return res;
        }


        public double time_correction() throws Exception {
            return time_correction(FOREVER);
        }

        public double pull_sample(float[] sample, double timeout) throws Exception {
            int[] ec = {0};
            double res = inst.lsl_pull_sample_f(obj, sample, sample.length, timeout, ec);
            check_error(ec);
            return res;
        }

        public double pull_sample(float[] sample) throws Exception {
            return pull_sample(sample, FOREVER);
        }

        public double pull_sample(double[] sample, double timeout) throws Exception {
            int[] ec = {0};
            double res = inst.lsl_pull_sample_d(obj, sample, sample.length, timeout, ec);
            check_error(ec);
            return res;
        }

        public double pull_sample(double[] sample) throws Exception {
            return pull_sample(sample, FOREVER);
        }

        public double pull_sample(int[] sample, double timeout) throws Exception {
            int[] ec = {0};
            double res = inst.lsl_pull_sample_i(obj, sample, sample.length, timeout, ec);
            check_error(ec);
            return res;
        }

        public double pull_sample(int[] sample) throws Exception {
            return pull_sample(sample, FOREVER);
        }

        public double pull_sample(short[] sample, double timeout) throws Exception {
            int[] ec = {0};
            double res = inst.lsl_pull_sample_s(obj, sample, sample.length, timeout, ec);
            check_error(ec);
            return res;
        }

        public double pull_sample(short[] sample) throws Exception {
            return pull_sample(sample, FOREVER);
        }

        public double pull_sample(byte[] sample, double timeout) throws Exception {
            int[] ec = {0};
            double res = inst.lsl_pull_sample_c(obj, sample, sample.length, timeout, ec);
            check_error(ec);
            return res;
        }

        public double pull_sample(byte[] sample) throws Exception {
            return pull_sample(sample, FOREVER);
        }

        public double pull_sample(String[] sample, double timeout) throws Exception {
            int[] ec = {0};
            double res = inst.lsl_pull_sample_str(obj, sample, sample.length, timeout, ec);
            check_error(ec);
            return res;
        }


        public double pull_sample(String[] sample) throws Exception {
            return pull_sample(sample, FOREVER);
        }

        public int pull_chunk(float[] data_buffer, double[] timestamp_buffer, double timeout) throws Exception {
            int[] ec = {0};
            long res = inst.lsl_pull_chunk_f(obj, data_buffer, timestamp_buffer, data_buffer.length, timestamp_buffer.length, timeout, ec);
            check_error(ec);
            return (int) res;
        }

        public int pull_chunk(float[] data_buffer, double[] timestamp_buffer) throws Exception {
            return pull_chunk(data_buffer, timestamp_buffer, 0.0);
        }

        public int pull_chunk(double[] data_buffer, double[] timestamp_buffer, double timeout) throws Exception {
            int[] ec = {0};
            long res = inst.lsl_pull_chunk_d(obj, data_buffer, timestamp_buffer, data_buffer.length, timestamp_buffer.length, timeout, ec);
            check_error(ec);
            return (int) res;
        }

        public int pull_chunk(double[] data_buffer, double[] timestamp_buffer) throws Exception {
            return pull_chunk(data_buffer, timestamp_buffer, 0.0);
        }

        public int pull_chunk(short[] data_buffer, double[] timestamp_buffer, double timeout) throws Exception {
            int[] ec = {0};
            long res = inst.lsl_pull_chunk_s(obj, data_buffer, timestamp_buffer, data_buffer.length, timestamp_buffer.length, timeout, ec);
            check_error(ec);
            return (int) res;
        }

        public int pull_chunk(short[] data_buffer, double[] timestamp_buffer) throws Exception {
            return pull_chunk(data_buffer, timestamp_buffer, 0.0);
        }

        public int pull_chunk(byte[] data_buffer, double[] timestamp_buffer, double timeout) throws Exception {
            int[] ec = {0};
            long res = inst.lsl_pull_chunk_c(obj, data_buffer, timestamp_buffer, data_buffer.length, timestamp_buffer.length, timeout, ec);
            check_error(ec);
            return (int) res;
        }

        public int pull_chunk(byte[] data_buffer, double[] timestamp_buffer) throws Exception {
            return pull_chunk(data_buffer, timestamp_buffer, 0.0);
        }

        public int pull_chunk(int[] data_buffer, double[] timestamp_buffer, double timeout) throws Exception {
            int[] ec = {0};
            long res = inst.lsl_pull_chunk_i(obj, data_buffer, timestamp_buffer, data_buffer.length, timestamp_buffer.length, timeout, ec);
            check_error(ec);
            return (int) res;
        }

        public int pull_chunk(int[] data_buffer, double[] timestamp_buffer) throws Exception {
            return pull_chunk(data_buffer, timestamp_buffer, 0.0);
        }

        public int pull_chunk(String[] data_buffer, double[] timestamp_buffer, double timeout) throws Exception {
            int[] ec = {0};
            long res = inst.lsl_pull_chunk_str(obj, data_buffer, timestamp_buffer, data_buffer.length, timestamp_buffer.length, timeout, ec);
            check_error(ec);
            return (int) res;
        }

        public int pull_chunk(String[] data_buffer, double[] timestamp_buffer) throws Exception {
            return pull_chunk(data_buffer, timestamp_buffer, 0.0);
        }

        public int samples_available() {
            return (int) inst.lsl_samples_available(obj);
        }

        public boolean was_clock_reset() {
            return (int) inst.lsl_was_clock_reset(obj) != 0;
        }
    }


    public static class XMLElement {
        private Pointer obj;


        public XMLElement(Pointer handle) {
            obj = handle;
        }

        public XMLElement first_child() {
            return new XMLElement(inst.lsl_first_child(obj));
        }

        public XMLElement last_child() {
            return new XMLElement(inst.lsl_last_child(obj));
        }

        public XMLElement next_sibling() {
            return new XMLElement(inst.lsl_next_sibling(obj));
        }

        public XMLElement previous_sibling() {
            return new XMLElement(inst.lsl_previous_sibling(obj));
        }


        public XMLElement parent() {
            return new XMLElement(inst.lsl_parent(obj));
        }

        public XMLElement child(String name) {
            return new XMLElement(inst.lsl_child(obj, name));
        }

        public XMLElement next_sibling(String name) {
            return new XMLElement(inst.lsl_next_sibling_n(obj, name));
        }


        public XMLElement previous_sibling(String name) {
            return new XMLElement(inst.lsl_previous_sibling_n(obj, name));
        }

        public boolean empty() {
            return inst.lsl_empty(obj) != 0;
        }

        public boolean is_text() {
            return inst.lsl_is_text(obj) != 0;
        }

        public String name() {
            return (inst.lsl_name(obj));
        }

        public String value() {
            return (inst.lsl_value(obj));
        }

        public String child_value() {
            return (inst.lsl_child_value(obj));
        }


        public String child_value(String name) {
            return (inst.lsl_child_value_n(obj, name));
        }

        public XMLElement append_child_value(String name, String value) {
            return new XMLElement(inst.lsl_append_child_value(obj, name, value));
        }

        public XMLElement prepend_child_value(String name, String value) {
            return new XMLElement(inst.lsl_prepend_child_value(obj, name, value));
        }

        public boolean set_child_value(String name, String value) {
            return inst.lsl_set_child_value(obj, name, value) != 0;
        }

        public boolean set_name(String rhs) {
            return inst.lsl_set_name(obj, rhs) != 0;
        }

        public boolean set_value(String rhs) {
            return inst.lsl_set_value(obj, rhs) != 0;
        }

        public XMLElement append_child(String name) {
            return new XMLElement(inst.lsl_append_child(obj, name));
        }

        public XMLElement prepend_child(String name) {
            return new XMLElement(inst.lsl_prepend_child(obj, name));
        }

        public XMLElement append_copy(XMLElement e) {
            return new XMLElement(inst.lsl_append_copy(obj, e.obj));
        }

        public XMLElement prepend_copy(XMLElement e) {
            return new XMLElement(inst.lsl_prepend_copy(obj, e.obj));
        }

        public void remove_child(String name) {
            inst.lsl_remove_child_n(obj, name);
        }

        public void remove_child(XMLElement e) {
            inst.lsl_remove_child(obj, e.obj);
        }
    }


    public static class ContinuousResolver {
        private Pointer obj;

        public ContinuousResolver(double forget_after) {
            obj = inst.lsl_create_continuous_resolver(forget_after);
        }

        public ContinuousResolver() {
            obj = inst.lsl_create_continuous_resolver(5.0);
        }

        public ContinuousResolver(String prop, String value, double forget_after) {
            obj = inst.lsl_create_continuous_resolver_byprop(prop, value, forget_after);
        }

        public ContinuousResolver(String prop, String value) {
            obj = inst.lsl_create_continuous_resolver_byprop(prop, value, 5.0);
        }

        public ContinuousResolver(String pred, double forget_after) {
            obj = inst.lsl_create_continuous_resolver_bypred(pred, forget_after);
        }

        public ContinuousResolver(String pred) {
            obj = inst.lsl_create_continuous_resolver_bypred(pred, 5.0);
        }

        void close() {
            inst.lsl_destroy_continuous_resolver(obj);
        }

        public StreamInfo[] results() {
            Pointer[] buf = new Pointer[1024];
            int num = inst.lsl_resolver_results(obj, buf, buf.length);
            StreamInfo[] res = new StreamInfo[num];
            for (int k = 0; k < num; k++)
                res[k] = new StreamInfo(buf[k]);
            return res;
        }
    }

    public static class TimeoutException extends Exception {
        public TimeoutException(String message) {
            super(message);
        }
    }

    public static class LostException extends Exception {
        public LostException(String message) {
            super(message);
        }
    }

    public static class ArgumentException extends Exception {
        public ArgumentException(String message) {
            super(message);
        }
    }

    public static class InternalException extends Exception {
        public InternalException(String message) {
            super(message);
        }
    }

    public class ChannelFormat {
        public static final int float32 = 1;
        public static final int double64 = 2;
        public static final int string = 3;
        public static final int int32 = 4;
        public static final int int16 = 5;
        public static final int int8 = 6;
        public static final int int64 = 7;
        public static final int undefined = 0;
    }

    public class ProcessingOptions {
        public static final int proc_none = 0;
        public static final int proc_clocksync = 1;
        public static final int proc_dejitter = 2;
        public static final int proc_monotonize = 4;
        public static final int proc_threadsafe = 8;
        public static final int proc_ALL = 1 | 2 | 4 | 8;
    }

    public class ErrorCode {
        public static final int no_error = 0;
        public static final int timeout_error = -1;
        public static final int lost_error = -2;
        public static final int argument_error = -3;
        public static final int internal_error = -4;
    }
}
