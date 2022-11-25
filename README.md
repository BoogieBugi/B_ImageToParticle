
# B_ImageToParticle
### 이미지 파일을 통해 파티클을 생성하는 라이브러리
### Create Particle Shapes by Image.
<div>
📌 Connects : <a href="https://github.com/BoogieBugi"><img src=https://img.shields.io/badge/Github-000000?logo=github&style=flat-square>
<a href="https://youtube.com/@Bu_Gi"><img src=https://img.shields.io/badge/Youtube-red?logo=youtube&style=flat-square>
</div>
<div>
🔎 Language : <img src=https://img.shields.io/badge/Java-orange?&style=flat-square>
</div>

## Usages
### ImageLoader.java
> 이미지 파일을 관리하는 클래스입니다.  
> Management image files.
* 임포트 ( import )
  ```java
  import com.bugi.b_imagetoparticle.ImageLoader
  ```

* 이미지 등록 ( registerImage )
  > 리소스 파일에서 이미지를 불러와 등록합니다.  
  > Register data of image in plugin's resources folder.
  ```java
  ImageLoader.registerImage(Plugin plugin, String imageName, String fileName)
  ```

* 이미지 등록 취소 ( unregisterImage )
  > 등록되어 있는 이미지 데이터를 제거합니다.  
  > Unregister data of image.
  ```java
  ImageLoader.unregisterImage(String imageName)
  ```

* 이미지 등록 확인 ( isRegistered )
  > 해당 이미지가 등록되어 있는지 확인합니다.  
  > Return if image is alreardy registered.
  ```java
  ImageLoader.isRegistered(String imageName)
  ```

* 이미지 리스트 반환 ( getImageList )
  > 등록된 이미지 리스트를 불러옵니다.  
  > Get name list of registered images.
  ```java
  ImageLoader.getImageList()
  ```

* 이미지 데이터 반환 ( getImageData )
  > 등록된 이미지의 RGB 데이터를 불러옵니다.  
  > get RGB data of image.
  ```java
  ImageLoader.getImageData(String imageName)
  ```

### ImageParticle.java
> 이미지 데이터를 파티클로 구현하는 클래스입니다.
> Spawn particle by image data.
* 임포트 ( import )
  ```java
  import com.bugi.b_imagetoparticle.ImageParticle
  ```

* 생성자 ( constructor )
  ```java
  public ImageParticle(World world, Location loc, String imageName)
  ```

* 파티클 생성 ( spawnParticle )
  > 파티클을 생성합니다.  
  > Spawn Particle.
  ```java
  ImageParticle.spawnParticle(float rotateY, float rotateP, float interval, float size)
  ```
