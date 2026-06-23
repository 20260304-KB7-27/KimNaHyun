package org.scoula.travel.dao;

import org.scoula.database.JDBCUtil;
import org.scoula.travel.domain.TravelImageVO;
import org.scoula.travel.domain.TravelVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelDaoImpl implements TravelDao{
    Connection conn = JDBCUtil.getConnection();

    @Override
    public void insert(TravelVO travel) {
        String sql = "INSERT INTO tbl_travel(no, district, title, description, address, phone) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, travel.getNo());
            pstmt.setString(2, travel.getDistrict());
            pstmt.setString(3, travel.getTitle());
            pstmt.setString(4, travel.getDescription());
            pstmt.setString(5, travel.getAddress());
            pstmt.setString(6, travel.getPhone());

            int count = pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertImage(TravelImageVO image) {
        String sql = "insert into tbl_travel_image(filename, travel_no) values(?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, image.getFilename());
            pstmt.setLong(2, image.getTravelNo());
            int count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from tbl_travel";

        try(PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getDistricts() {
        List<String> districts = new ArrayList<>();
        String sql = "select distinct(district) from tbl_travel ORDER BY district";

        try(PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            while (rs.next()){
                districts.add(rs.getString("district"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return districts;
    }

    private TravelVO map (ResultSet rs) throws SQLException {
        return TravelVO.builder()
                .no(rs.getLong("no");
                .build();
    }

    @Override
    public List<TravelVO> getTravels() {
        List<TravelVO> travels = new ArrayList<>();

        return travels;
    }

    @Override
    public List<TravelVO> getTravels(int page) {
        return List.of();
    }

    @Override
    public List<TravelVO> getTravels(String district) {
        return List.of();
    }

    @Override
    public Optional<TravelVO> getTravel(Long no) {
        TravelVO travel = null;
        String sql = """
                select t.*, ti.no as tino, ti.filename, ti.travel_no
                from tbl_travel t
                    left outer join tbl_travel_image ti
                    on t.no = ti.travel_no
                    
                where t.no = ?;
       
                """;

        try(PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setLong(1, no);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    travel = map(rs);

                    List<TravelImageVO> images = new ArrayList<>();
                    try{
                        do{
                            TravelImageVO image = mapImage(rs);
                            images.add(image);
                        }while (rs.next());
                    }catch (SQLException e){}

                    travel.setImages(image);
                    return Optional.of(travel);


                }
                else return Optional.empty();
                

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
