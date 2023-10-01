package com.example.frament_tk3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    private GridView gridView;

    private  View view;

    private  ISendDateListener mISendDateListener;

    public interface ISendDateListener{
//        void sendData(int hinh, String sanpham,String gia, String thongtin);
        void sendDataHinh(int hinh);
        void sendDataSanPham(String sanpham);
        void sendDataGia(String gia);
        void sendDataThongTin(String thongtin);
    }


    public Fragment1() {
        // Required empty public constructor
    }

    String[] sanpham={
            "Đồng hồ Nam QQ S306J301Y",
            " Đồng hồ Nữ Elio ES051-01",
            "Đồng hồ Nữ ELIO EL086-02",
            "Đồng hồ nữ Elle Molitor Three-Hand thép",
            "Đồng hồ nam Fossil FB-01 dây thép không gỉ",
            "SMILE KID 43 mm Trẻ em SL087-02",
            "SMILE KID 37 mm Trẻ em SL084-01",
            "SMILE KID 39 mm Trẻ em SL083-03",
            "SMILE KID 39 mm Trẻ em SL083-02",
            "SMILE KID 38 mm Trẻ em SL086-01",
            "SMILE KID 41 mm Trẻ em SL088-02",
            "SMILE KID 41 mm Trẻ em SL088-01",
            "SCITIZEN 40 mm Cơ Nam NH8391-51E",
            "CITIZEN 40 mm Cơ Nam NH8391-51X",
            "CITIZEN 40 mm Cơ Nam NH8391-51L"
    };
    String[] gia = {
            "6.856.000₫",
            "6.856.000₫",
            "6.856.000₫",
            "219.000₫",
            "149.000₫",
            "657.000₫",
            "657.000₫",
            "715.000₫",
            "862.000₫",
            "862.000₫",
            "149.000₫",
            "219.000₫",
            "219.000₫",
            "928.000₫",
            "679.000₫"

    } ;
    int[] imageId = {
            R.drawable.dh1,
            R.drawable.dh2,
            R.drawable.dh3,
            R.drawable.dh4,
            R.drawable.dh5,
            R.drawable.dh6,
            R.drawable.dh7,
            R.drawable.dh8,
            R.drawable.dh9,
            R.drawable.dh10,
            R.drawable.dh11,
            R.drawable.dh12,
            R.drawable.dh13,
            R.drawable.dh14,
            R.drawable.dh15

    };
    String[] thongtinSp = {
            "- Sản phẩm đến từ hãng đồng hồ Rossini. Hãng đồng hồ có những sản phẩm luôn có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng với nhiều thiết kế, mẫu mã đa dạng, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ sở hữu đường kính mặt 30 mm, độ rộng dây 7 mm.\n" +
                    "\n" +
                    "- Chất liệu của khung viền được làm từ chất liệu thép không gỉ có độ cứng cao, bền bỉ và chống ăn mòn tốt. Dây đeo được làm từ da tổng hợp đem đến cảm giác thoải mái khi đeo, ôm trọn cổ tay người dùng.\n" +
                    "\n" +
                    "- Đồng hồ được trang bị tính năng lịch ngày, thuận tiện theo dõi.\n" +
                    "\n" +
                    "- Khả năng chống nước của mẫu đồng hồ nữ này là 3 ATM có thể đi mưa, rửa tay. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "Năng động, tinh nghịch\n" +
                    "Sản phẩm đồng hồ mang thương hiệu Smile Kid với nhiều mẫu mã năng động, trẻ trung nhưng không kém phần tinh tế và sang trọng, phù hợp với các em nhỏ.",
            "- Mẫu đồng hồ này đến từ hãng đồng hồ Orient - Nhật Bản. Thương hiệu với những mẫu đồng hồ mang phong cách thời thượng, sang trọng đầy sức hút.\n" +
                    "\n" +
                    "- Mẫu đồng hồ Orient mặt lửa này là đồng hồ cơ (máy Automatic) hoạt động ổn định cho độ sai số thấp và thời gian trữ cót khoảng 40 tiếng. Đặc biệt sản phẩm này là phiên bản đặc biệt dành cho Việt Nam.\n" +
                    "\n" +
                    "- Sở hữu đường kính mặt 41.7 mm.\n" +
                    "\n" +
                    "- Mặt kính của đồng hồ được làm từ kính cứng, có độ cứng cáp, độ trong suốt cao, chống trầy xước nứt vỡ cực tốt. Khung viền và dây đeo được làm từ thép không gỉ 316L có khả năng chống chịu ăn mòn cao, độ cứng và độ bền cao. \n" +
                    "\n" +
                    "- Mẫu đồng hồ nam được trang bị hệ số chống nước lên đến 5 ATM, bạn có thể an tâm khi mang đi tắm và đi mưa, tuy nhiên không nên đeo đi bơi, lặn.",
            "- Mẫu đồng hồ này đến từ hãng đồng hồ Orient - Nhật Bản. Thương hiệu với những mẫu đồng hồ mang phong cách thời thượng, sang trọng đầy sức hút.\n" +
                    "\n" +
                    "- Mẫu đồng hồ Orient mặt lửa này là đồng hồ cơ (máy Automatic) hoạt động ổn định cho độ sai số thấp và thời gian trữ cót khoảng 40 tiếng. Đặc biệt sản phẩm này là phiên bản đặc biệt dành cho Việt Nam.\n" +
                    "\n" +
                    "- Sở hữu đường kính mặt 41.7 mm.\n" +
                    "\n" +
                    "- Mặt kính của đồng hồ được làm từ kính cứng, có độ cứng cáp, độ trong suốt cao, chống trầy xước nứt vỡ cực tốt. Khung viền và dây đeo được làm từ thép không gỉ 316L có khả năng chống chịu ăn mòn cao, độ cứng và độ bền cao. \n" +
                    "\n" +
                    "- Mẫu đồng hồ nam được trang bị hệ số chống nước lên đến 5 ATM, bạn có thể an tâm khi mang đi tắm và đi mưa, tuy nhiên không nên đeo đi bơi, lặn.",
            "- Đến từ thương hiệu Citizen nổi tiếng của Nhật Bản.\n" +
                    "\n" +
                    "- Đồng hồ nam sở hữu đường kính mặt 40 mm, độ rộng dây 20 mm.\n" +
                    "\n" +
                    "- Kết cấu khung viền và dây đeo của đồng hồ Citizen được làm bằng thép không gỉ 316L và thép không gỉ cứng cáp với khả năng chống oxi hóa cực kì hiệu quả, giúp bảo vệ an toàn những bộ phận bên trong của đồng hồ và duy trì được tuổi thọ lâu dài. Ngoài ra, rất dễ vệ sinh khi bám bụi bẩn.\n" +
                    "\n" +
                    "- Tính năng lịch thứ, lịch ngày giúp bạn quản lí thời gian hiệu quả hơn trong công việc và cuộc sống.\n" +
                    "\n" +
                    "- Khả năng chống nước 5 ATM thoải mái mái rửa tay, đi mưa và đi tắm mà không lo sợ nước sẽ gây hư hỏng cho đồng hồ. Lưu ý: không đeo khi đi bơi lội hoặc lặn.",
            "Sản phẩm đồng hồ mang thương hiệu Smile Kid với nhiều mẫu mã năng động, trẻ trung nhưng không kém phần tinh tế và sang trọng, phù hợp với các em nhỏ.",
            "- Sản phẩm này của hãng đồng hồ Rossini, hãng đồng hồ với những sản phẩm có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng cũng như sự đa dạng về thiết kế, mẫu mã, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ sở hữu đường kính mặt 41 mm, độ rộng dây 20 mm.\n" +
                    "\n" +
                    "- Khung viền và dây đeo đều được làm từ thép không gỉ. Chất liệu này có độ cứng cao, bền bỉ, có khả năng chống ăn mòn tốt, chống oxi hoá, chống chịu mọi thời tiết. \n" +
                    "\n" +
                    "- Đồng hồ được trang bị tính năng lịch ngày, thuận tiện theo dõi.\n" +
                    "\n" +
                    "- Hệ số kháng nước của mẫu đồng hồ nam này là 3 ATM, có thể đi mưa và rửa tay. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "- Sản phẩm này của hãng đồng hồ Rossini, hãng đồng hồ với những sản phẩm có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng cũng như sự đa dạng về thiết kế, mẫu mã, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ sở hữu đường kính mặt 39 mm, độ rộng dây 19 mm.\n" +
                    "\n" +
                    "- Khung viền và dây đeo đều được làm từ thép không gỉ. Chất liệu này có độ cứng cao, bền bỉ, có khả năng chống ăn mòn tốt, chống oxi hoá, chống chịu mọi thời tiết. \n" +
                    "\n" +
                    "- Hệ số kháng nước của mẫu đồng hồ nam này là 3 ATM, có thể đi mưa và rửa tay. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "- Sản phẩm này của hãng đồng hồ Rossini, hãng đồng hồ với những sản phẩm có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng cũng như sự đa dạng về thiết kế, mẫu mã, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ sở hữu đường kính mặt 41 mm, độ rộng dây 18 mm.\n" +
                    "\n" +
                    "- Chất liệu của khung viền được làm từ thép không gì, chất liệu này có độ cứng cao, bền bỉ, có khả năng chống ăn mòn tốt, chống oxi hoá, chống chịu mọi thời tiết. Dây đeo được làm từ da tổng hợp mềm mịn, đem lại cảm giác thoải mái, ôm trọn cổ tay khi đeo.\n" +
                    "\n" +
                    "- Đồng hồ được trang bị kim dạ quang, tính năng lịch ngày, thuận tiện theo dõi.\n" +
                    "\n" +
                    "- Hệ số kháng nước của mẫu đồng hồ nam này là 3 ATM, có thể đi mưa và rửa tay. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "- Sản phẩm này của hãng đồng hồ Rossini, hãng đồng hồ với những sản phẩm có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng cũng như sự đa dạng về thiết kế, mẫu mã, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ sở hữu đường kính mặt 42 mm, độ rộng dây 19 mm.\n" +
                    "\n" +
                    "- Khung viền và dây đeo đều được làm từ thép không gỉ. Chất liệu này có độ cứng cao, bền bỉ, có khả năng chống ăn mòn tốt, chống oxi hoá, chống chịu mọi thời tiết. \n" +
                    "\n" +
                    "- Hệ số kháng nước của mẫu đồng hồ nam này là 3 ATM, có thể đi mưa và rửa tay. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "- Sản phẩm này của hãng đồng hồ Rossini, hãng đồng hồ với những sản phẩm có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng cũng như sự đa dạng về thiết kế, mẫu mã, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ sở hữu đường kính mặt 42 mm, độ rộng dây 19 mm.\n" +
                    "\n" +
                    "- Khung viền và dây đeo đều được làm từ thép không gỉ. Chất liệu này có độ cứng cao, bền bỉ, có khả năng chống ăn mòn tốt, chống oxi hoá, chống chịu mọi thời tiết. \n" +
                    "\n" +
                    "- Hệ số kháng nước của mẫu đồng hồ nam này là 3 ATM, có thể đi mưa và rửa tay. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "- Sản phẩm này của hãng đồng hồ Rossini, hãng đồng hồ với những sản phẩm có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng cũng như sự đa dạng về thiết kế, mẫu mã, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ sở hữu đường kính mặt 42 mm, độ rộng dây 19 mm.\n" +
                    "\n" +
                    "- Khung viền và dây đeo đều được làm từ thép không gỉ. Chất liệu này có độ cứng cao, bền bỉ, có khả năng chống ăn mòn tốt, chống oxi hoá, chống chịu mọi thời tiết. \n" +
                    "\n" +
                    "- Hệ số kháng nước của mẫu đồng hồ nam này là 3 ATM, có thể đi mưa và rửa tay. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "- Sản phẩm này của hãng đồng hồ Rossini, hãng đồng hồ với những sản phẩm có mức giá hợp lí nhưng vẫn đảm bảo được chất lượng cũng như sự đa dạng về thiết kế, mẫu mã, tính năng phù hợp với đại đa số nhu cầu của người dùng.\n" +
                    "\n" +
                    "- Đồng hồ có thiết kế đường kính mặt 40 mm, độ rộng dây 18 mm.\n" +
                    "\n" +
                    "- Chất liệu của khung viền có độ cứng cao, bền bỉ, có khả năng chống ăn mòn tốt, chống oxi hoá, chống chịu mọi thời tiết. Dây đeo làm từ da tổng hợp mềm mịn, thoải mái khi đeo.\n" +
                    "\n" +
                    "- Đồng hồ được trang bị tính năng lịch ngày, thuận tiện theo dõi.\n" +
                    "\n" +
                    "- Mẫu đồng hồ nam này có thể đi mưa và rửa tay vì có hệ số kháng nước là 3 ATM. Lưu ý không sử dụng khi đi tắm, đi bơi, lặn.",
            "Được thiết kế từ những vật liệu tinh xảo cùng công nghệ chế tác hàng đầu nên không ngạc nhiên Skmei phát triển rất nhiều chủng loại mẫu mã đồng hồ đáp ứng mọi nhu cầu từ đơn giản đến phức tạp của người tiêu dùng từ đồng hồ nam, nữ thời trang, đồng hồ thể thao, đến đồng hồ trẻ em.",
            "Được thiết kế từ những vật liệu tinh xảo cùng công nghệ chế tác hàng đầu nên không ngạc nhiên Skmei phát triển rất nhiều chủng loại mẫu mã đồng hồ đáp ứng mọi nhu cầu từ đơn giản đến phức tạp của người tiêu dùng từ đồng hồ nam, nữ thời trang, đồng hồ thể thao, đến đồng hồ trẻ em."


    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mISendDateListener= (ISendDateListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_1, container, false);
        gridView=view.findViewById(R.id.gridView) ;
        CustomGrid adapter = new CustomGrid(view.getContext(), sanpham, imageId,gia);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mISendDateListener.sendDataHinh(imageId[+i]);
                mISendDateListener.sendDataSanPham(sanpham[+i]);
                mISendDateListener.sendDataGia(gia[+i]);
                mISendDateListener.sendDataThongTin(thongtinSp[i]);
               // Toast.makeText(view.getContext(), "You Clicked at " +sanpham[+ i], Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(), "You Clicked at " +sanpham[+ i], Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}