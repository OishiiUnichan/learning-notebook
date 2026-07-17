package model;
import java.io.Serializable;

public class SiteEV implements Serializable{
    //フィールド
    private int like; //いいねの数
    private int dislike; //よくないねの数

/*     public SiteEV(){
*        this.setLike(0);
*        this.setDislike(0);
*    }
*/

    //アクセサメソッド
    public int getLike(){return like;}
    public void setLike(int like){this.like = like;}

    public int getDislike(){return dislike;}
    public void setDislike(int dislike) {this.dislike = dislike;}
}