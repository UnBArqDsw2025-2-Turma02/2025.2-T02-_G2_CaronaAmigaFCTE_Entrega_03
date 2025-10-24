public interface LocationService {
    String geocode(String endereco);
    String reverse(String latLon);
    String route(String origDest);
}