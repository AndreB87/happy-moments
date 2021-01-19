package de.ansaru.happymoments.web.controller.moments;

import de.ansaru.happymoments.model.AbstractController;
import de.ansaru.happymoments.model.Moment;
import de.ansaru.happymoments.model.MomentFile;
import de.ansaru.happymoments.model.utils.FileType;
import de.ansaru.happymoments.services.MomentFileService;
import de.ansaru.happymoments.services.MomentService;
import de.ansaru.happymoments.services.UserService;
import de.ansaru.happymoments.web.controller.moments.dtos.ListMomentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MomentsController extends AbstractController {

    @Autowired
    private MomentService momentService;

    @Autowired
    private MomentFileService momentFileService;

    @Autowired
    private UserService userService;

    @GetMapping("/moments")
    public String listMoments(Model model) {
        Optional<Long> optionalUserId = userService.getUserIdByEmail(getUsername());

        if (optionalUserId.isPresent()) {
            long userId = optionalUserId.get();

            List<ListMomentDto> myMoments = new ArrayList<>();
            momentService.getMyMoments(userId).forEach(
                    m -> myMoments.add(new ListMomentDto(m.getId(), m.getName()))
            );

            List<ListMomentDto> sharedMoments = new ArrayList<>();
            momentService.getSharedMoments(userId).forEach(
                    m -> sharedMoments.add(new ListMomentDto(m.getId(), m.getName()))
            );

            model.addAttribute("myMoments", myMoments);
            model.addAttribute("sharedMoments", sharedMoments);

            return "moments/list";
        }

        return "error";
    }

    @GetMapping("/moments/show")
    public String showMoment(
            @RequestParam(name = "id")
                long id,
            Model model) {

        Optional<Long> optionalUserId = userService.getUserIdByEmail(getUsername());
        if (optionalUserId.isPresent()) {
            long userId = optionalUserId.get();
            Optional<Moment> optionalMoment = momentService.getMoment(id);

            if (optionalMoment.isPresent())  {
                Moment moment = optionalMoment.get();

                if (userId == moment.getOwner() || moment.getUserIds().contains(userId)) {
                    model.addAttribute("userId", userId);
                    model.addAttribute("ownerId", moment.getOwner());
                    model.addAttribute("momentId", moment.getId());
                    model.addAttribute("fotos", momentFileService.getFilesByMoment(moment.getId()));

                    return "moments/show";
                } else {
                    model.addAttribute("error-msg", "Du hast keinen Zugriff auf diesen Moment!");
                }
            } else {
                model.addAttribute("error-msg", "Der Moment konnte nicht gefunden werden");
            }
        } else {
            model.addAttribute("error-msg", "Ein unbekannter Fehler ist aufgetreten.");
        }

        return "error";
    }

    @GetMapping("/moments/create")
    public String createMoment(Model model) {
        return "moments/edit";
    }

    @PostMapping("/moments/create")
    public String createMoment(
            @RequestParam(name = "name", required = true)
                String momentName,
            @RequestParam(name = "description", required = true)
                String momentDescription,
            @RequestParam(name = "date", required = true)
                String momentDate,
            Model model) {

        final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<Long> optionalUserId = userService.getUserIdByEmail(getUsername());

        if (optionalUserId.isPresent()) {
            Optional<Moment> optionalMoment = momentService.createMoment(
                    momentName,
                    momentDescription,
                    LocalDate.parse(momentDate, dateTimeFormatter),
                    optionalUserId.get());

            if (optionalMoment.isPresent()) {
                Moment moment = optionalMoment.get();

                return "redirect:/moments/show";
            } else {

                model.addAttribute("error-msg", "Ein Fehler ist aufgetreten.");
                return "error";
            }
        } else {

            model.addAttribute("error-msg", "Der Benutzer wurde nicht gefunden.");
            return "error";
        }
    }

    @GetMapping("/moments/addfile")
    public String addFile(
            @RequestParam(name = "momentId")
                    long momentId,
            Model model) {

        return "moment/files/add";
    }

    @PostMapping("/moments/addfile")
    public String addFile(
            @RequestParam(name = "momentId")
                long momentId,
            @RequestParam(name = "description")
                String description,
            @RequestParam(name = "date")
                LocalDate date,
            @RequestParam(name = "file")
                File file,
            @RequestParam(name = "fileType")
                String fileType,
            Model model) {

        Optional<Long> optionalUserId = userService.getUserIdByEmail(getUsername());

        if (optionalUserId.isPresent()) {
            long userId = optionalUserId.get();
            Optional<Moment> optionalMoment = momentService.getMoment(momentId);

            if (optionalMoment.isPresent()) {
                Moment moment = optionalMoment.get();
                if (moment.getOwner() == userId || moment.getUserIds().contains(userId)) {

                    Optional<MomentFile> optionalFile = momentFileService.createFile(
                            momentId,
                            description,
                            date,
                            file,
                            FileType.valueOf(fileType)
                    );

                    return optionalFile.isPresent()? "moment/files/add" : "error";
                }
            }
        }
        return "error";
    }

}
