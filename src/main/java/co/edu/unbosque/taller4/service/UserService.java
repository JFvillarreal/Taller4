package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.User;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    public Optional<List<User>> getUsers() throws IOException {

        List<User> users;

        try (InputStream is = UserService.class.getClassLoader()
                .getResourceAsStream("/users.csv")) {

            if (is == null) {
                return Optional.empty();
            }

            HeaderColumnNameMappingStrategy<User> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(User.class);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                System.out.println(br.toString()+" este es el br");
                System.out.println(is.toString()+" este es el is");
                System.out.println();
                CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(br)
                        .withType(User.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                users = csvToBean.parse();

            }
        }

        return Optional.of(users);
    }
    public void WriteCvs(User nuevo_usuario) throws IOException {

        File file = new File("./users2.csv");
        List<User> Users= getUsers().get();
        System.out.println("muestra verdadero si se peude escribi el archivo "+file.canWrite());
        System.out.println("este es el nomre del usuario desde el wrutecvs "+nuevo_usuario.getUsername());
        Users.add(nuevo_usuario);
        System.out.println("esta liena se crea despues de haber ceado el users4.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file,true);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "username","password","role","Fcoins" };
            System.out.println(header[0]+header[1]+header[2] +"estos son los headers");
            writer.writeNext(header);
            System.out.println("esta es la linea de 65 con el nombre del nuevo usuario" +nuevo_usuario.getUsername());
            // add data to csv
            for(int i=0;i<Users.size();i++){
                String[] next= {Users.get(i).getUsername(), Users.get(i).getPassword(), Users.get(i).getRole()};
                writer.writeNext(next);
                System.out.println(next+" este es el next de cada linea y el username "+Users.get(i).getUsername());
            }

            // closing writer connection
            writer.close();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void writecsv2(User nuevo_usuario) throws IOException {
        ArrayList<User> usuario = new ArrayList<>();
        CSVWriter csvWriter = new CSVWriter(new FileWriter("users.csv"));
        usuario.add(nuevo_usuario);
        System.out.println("esta es la linea despues de añadir el usuario a csv");
        for (User ususer : usuario){
            csvWriter.writeNext(ususer.toarray());
        }
        System.out.println("en esta linea se supone que se esta escribiendo el csv");
        csvWriter.close();
    }
    public void createUser(String username, String password,String role,String Fcoins, String path) throws IOException {

        List<User>  lista=getUsers().get();
        User nuevo_usuario=new User();
        nuevo_usuario.setFcoins(Fcoins);
        nuevo_usuario.setUsername(username);
        nuevo_usuario.setPassword(password);
        nuevo_usuario.setRole(role);
        lista.add(nuevo_usuario);
        FileOutputStream os = new FileOutputStream(path + "WEB-INF/classes/" + "users.csv", false);
        String res="username,password,role,Fcoins";
        for(int i=0;i< lista.size();i++){
            res+="\n"+(lista.get(i).getUsername()+","+lista.get(i).getPassword()+","+lista.get(i).getRole()+","+lista.get(i).getFcoins());
        }
        os.write(res.getBytes());
        os.close();
    }

    public void mandarfcoins(String username,String password,String Fcoins,String path) throws IOException {
        List<User>  lista= getUsers().get();
        boolean res=false;
        int nfcoins=Integer.parseInt(Fcoins);
        for(int i=0;i< lista.size();i++){
            if(lista.get(i).getUsername().equals(username) && lista.get(i).getPassword().equals(password)){
                lista.get(i).setFcoins((Integer.parseInt(lista.get(i).getFcoins())+nfcoins)+"");
            }
        }
        FileOutputStream os = new FileOutputStream(path + "WEB-INF/classes/" + "users.csv", false);
        String res2="username,password,role,Fcoins";
        for(int i=0;i< lista.size();i++){
            res2+="\n"+(lista.get(i).getUsername()+","+lista.get(i).getPassword()+","+lista.get(i).getRole()+","+lista.get(i).getFcoins());
        }
        os.write(res2.getBytes());
        os.close();
    }

}

