package Controller;

import Model.Producto;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observables.MathObservable;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    private static String Variable1 = "c";
    private static String Variable2 = "a";
    private static List<Producto> list = new ArrayList<Producto>();

    public static void main(String[] args) {

        list.add(new Producto("tv", 500));
        list.add(new Producto("ps4", 300));
        list.add(new Producto("usb", 200));
        list.add(new Producto("laptop", 1000));
        list.add(new Producto("cast", 230));
        list.add(new Producto("camera", 500));

        // SUMANDO TODOS LOS PRODUCTOS
        Observable obs =
                Observable.from(list).map((result) -> {
                    Producto p = result;
                    return p.getPrecio();
                });

        MathObservable.sumInteger(obs).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("COMPLETADO !!!");
                System.out.println();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("LA SUMA DE LOS PRODUCTOS ES " + o);
            }
        });

        // PRECIO MAXIMO DE TODOS LOS PRODUCTOS
        MathObservable.max(obs).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("COMPLETADO !!!");
                System.out.println();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("EL PRECIO MAXIMO ES: " + o);
            }
        });

        // PROMEDIO DE TODOS LOS PRODUCTOS
        MathObservable.averageInteger(obs).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("COMPLETADO !!!");
                System.out.println();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("EL PROMEDIO DEL PRECIO DE TODOS LOS PRODUCTOS ES: " + o);
            }
        });

        // SUMATORIA DE PRECIOS QUE CONTENGA LA LETRA "C"
        Observable obs2 = Observable.from(list)
                .filter(new Func1<Producto, Boolean>() {
                    @Override
                    public Boolean call(Producto producto) {
                        return producto.getNombre().contains(Variable1);
                    }
                }).map((result) -> {
                    Producto p = result;
                    return p.getPrecio();
                });

        MathObservable.sumInteger(obs2).subscribe(new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("COMPLETADO !!!");
                System.out.println();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("LA SUMATORIA DE LOS QUE TIENEN LA LETRA C ES: " + o);
            }
        });

        // FILTRO DE LOS PRODUCTOS QUE TIENEN LA LETRA "a!

        Observable<Producto> obs3 = Observable.from(list);

        obs3.filter(new Func1<Producto, Boolean>() {
            @Override
            public Boolean call(Producto producto) {
                return producto.getNombre().contains(Variable2);
            }
        })
                .subscribe(new Subscriber<Producto>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("COMPLETADO !!!");
                        System.out.println();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable);
                    }

                    @Override
                    public void onNext(Producto producto) {
                        System.out.println("LOS PRODUCTOS QUE TIENEN LA LETRA a ES: " + producto.getNombre());
                    }
                });
    }
}
