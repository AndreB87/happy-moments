package de.ansaru.happymoments.web.controller.moments;

import de.ansaru.happymoments.model.moments.Moment;
import de.ansaru.happymoments.model.moments.MomentFile;
import de.ansaru.happymoments.model.moments.utils.FileType;
import de.ansaru.happymoments.services.moments.IMomentFileService;
import de.ansaru.happymoments.services.moments.IMomentService;
import de.ansaru.happymoments.services.user.IUserService;
import de.ansaru.happymoments.web.controller.AbstractController;
import de.ansaru.happymoments.web.controller.moments.dto.ListMomentDto;
import de.ansaru.happymoments.web.controller.moments.utils.de.Messages;
import de.ansaru.happymoments.web.controller.user.utils.de.Error;
import org.apache.log4j.Logger;
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

    private static final Logger LOG = Logger.getLogger(MomentsController.class);

    @Autowired
    private IMomentService momentService;

    @Autowired
    private IMomentFileService momentFileService;

    @Autowired
    private IUserService userService;

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
                    LOG.warn("user " + userId + "tries to access moment " + moment.getId() + " with no permission");
                    model.addAttribute("error-msg", Messages.NO_PERMISSION.getText());
                }
            } else {
                LOG.warn("moment " + id + "not found");
                model.addAttribute("error-msg", Messages.NOT_FOUND);
            }
        } else {
            LOG.error("unknown error in showMoment - id: " + id);
            model.addAttribute("error-msg", Error.UNKNOWN_ERROR.getText());
        }

        return "error";
    }

    @GetMapping("/moments/create")
    public String createMoment(Model model) {
        return "moments/edit";
    }

    @PostMapping("/moments/create")
    public String createMoment(
            @RequestParam(name = "name")
                String momentName,
            @RequestParam(name = "description")
                String momentDescription,
            @RequestParam(name = "date")
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

                model.addAttribute("error-msg", Messages.NOT_AVAILABLE);
                return "error";
            }
        } else {
            LOG.error("user " + getUsername() + " not found");
            model.addAttribute("error-msg", Error.USER_NOT_FOUND);
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
